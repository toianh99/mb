package com.xat.core.service.impl;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.dto.AdministrativeUnitDto;
import com.globits.core.repository.AdministrativeUnitRepository;
import com.globits.core.service.AdministrativeUnitService;
import com.globits.security.domain.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdministrativeUnitServiceImpl extends GenericServiceImpl<AdministrativeUnit, UUID> implements AdministrativeUnitService {
   @Autowired
   AdministrativeUnitRepository administrativeUnitRepository;

   private List<AdministrativeUnit> getListSub(AdministrativeUnit dep, int level) {
      ArrayList<AdministrativeUnit> retList = new ArrayList();
      dep.setLevel(level);
      retList.add(dep);
      if (dep.getSubAdministrativeUnits() != null && dep.getSubAdministrativeUnits().size() > 0) {
         Iterator iter = dep.getSubAdministrativeUnits().iterator();

         while(iter.hasNext()) {
            AdministrativeUnit d = (AdministrativeUnit)iter.next();
            retList.addAll(this.getListSub(d, level + 1));
         }
      }

      return retList;
   }

   public Page<AdministrativeUnit> getListAdministrativeUnitByTree(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      Page<AdministrativeUnit> page = this.administrativeUnitRepository.getPageRootAdministrativeUnit(pageable);
      ArrayList<AdministrativeUnit> retList = new ArrayList();

      for(int i = 0; i < page.getContent().size(); ++i) {
         if (page.getContent().get(i) != null) {
            List<AdministrativeUnit> childs = this.getListSub((AdministrativeUnit)page.getContent().get(i), 0);
            if (childs != null && childs.size() > 0) {
               retList.addAll(childs);
            }
         }
      }

      if (retList.size() > 0) {
         pageSize = retList.size();
      }

      Page<AdministrativeUnit> page = new PageImpl(retList, pageable, (long)pageSize);
      return page;
   }

   public Page<AdministrativeUnit> getPageRootAdministrativeUnit(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.administrativeUnitRepository.getPageRootAdministrativeUnit(pageable);
   }

   public List<AdministrativeUnitDto> getListRootAdministrativeUnitDto() {
      return this.administrativeUnitRepository.getListRootAdministrativeUnitDto();
   }

   public Page<AdministrativeUnit> getPageByParentId(UUID parentId, int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.administrativeUnitRepository.getPageByParentId(parentId, pageable);
   }

   public List<AdministrativeUnitDto> getListDtoByParentId(UUID parentId) {
      return this.administrativeUnitRepository.getListDtoByParentId(parentId);
   }

   public AdministrativeUnit updateAdministrativeUnit(AdministrativeUnit administrativeUnit, UUID administrativeUnitId) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      User modifiedUser = null;
      LocalDateTime currentDate = LocalDateTime.now();
      String currentUserName = "Unknown User";
      if (authentication != null) {
         modifiedUser = (User)authentication.getPrincipal();
         currentUserName = modifiedUser.getUsername();
      }

      AdministrativeUnit updateAdministrativeUnit = null;
      if (administrativeUnit.getId() != null) {
         updateAdministrativeUnit = (AdministrativeUnit)this.findById(administrativeUnit.getId());
      } else {
         updateAdministrativeUnit = (AdministrativeUnit)this.findById(administrativeUnitId);
      }

      if (updateAdministrativeUnit.getCreateDate() == null || updateAdministrativeUnit.getCreatedBy() == null) {
         updateAdministrativeUnit.setCreateDate(currentDate);
         updateAdministrativeUnit.setCreatedBy(currentUserName);
      }

      updateAdministrativeUnit.setModifyDate(currentDate);
      updateAdministrativeUnit.setModifiedBy(currentUserName);
      updateAdministrativeUnit.setCode(administrativeUnit.getCode());
      updateAdministrativeUnit.setName(administrativeUnit.getName());
      if (administrativeUnit.getParent() != null && administrativeUnit.getParent().getId() != null) {
         AdministrativeUnit parentAdministrativeUnit = (AdministrativeUnit)this.administrativeUnitRepository.getOne(administrativeUnit.getParent().getId());
         if (parentAdministrativeUnit.getId() != updateAdministrativeUnit.getId()) {
            updateAdministrativeUnit.setParent(parentAdministrativeUnit);
         }
      } else if (updateAdministrativeUnit.getParent() != null) {
         updateAdministrativeUnit.setParent((AdministrativeUnit)null);
      }

      updateAdministrativeUnit = (AdministrativeUnit)this.save(updateAdministrativeUnit);
      if (updateAdministrativeUnit.getParent() != null) {
         updateAdministrativeUnit.setParent(new AdministrativeUnit(updateAdministrativeUnit.getParent(), false));
      }

      updateAdministrativeUnit.setLat(administrativeUnit.getLat());
      updateAdministrativeUnit.setLng(administrativeUnit.getLng());
      return updateAdministrativeUnit;
   }

   public AdministrativeUnitDto saveAdministrativeUnit(AdministrativeUnitDto dto) {
      AdministrativeUnit administrativeUnit = null;
      if (dto != null && dto.getId() != null) {
         administrativeUnit = (AdministrativeUnit)this.administrativeUnitRepository.getOne(dto.getId());
      }

      if (administrativeUnit == null) {
         administrativeUnit = new AdministrativeUnit();
      }

      administrativeUnit.setCode(dto.getCode());
      administrativeUnit.setName(dto.getName());
      administrativeUnit.setLevel(dto.getLevel());
      if (dto.getParent() != null && dto.getParent().getId() != null) {
         AdministrativeUnit parentAdministrativeUnit = (AdministrativeUnit)this.administrativeUnitRepository.getOne(dto.getParent().getId());
         if (parentAdministrativeUnit.getId() != administrativeUnit.getId()) {
            administrativeUnit.setParent(parentAdministrativeUnit);
         }
      }

      administrativeUnit = (AdministrativeUnit)this.save(administrativeUnit);
      return new AdministrativeUnitDto(administrativeUnit);
   }

   public int deleteAdministrativeUnits(List<AdministrativeUnitDto> dtos) {
      if (dtos == null) {
         return 0;
      } else {
         int ret = 0;

         for(Iterator var3 = dtos.iterator(); var3.hasNext(); ++ret) {
            AdministrativeUnitDto dto = (AdministrativeUnitDto)var3.next();
            if (dto.getId() != null) {
               this.administrativeUnitRepository.deleteById(dto.getId());
            }
         }

         return ret;
      }
   }

   public Page<AdministrativeUnitDto> getListTree(Integer pageIndex, Integer pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      List<AdministrativeUnit> parents = this.administrativeUnitRepository.findTreeAdministrativeUnit(pageable);
      Long countElement = this.administrativeUnitRepository.countDadAdministrativeUnit();
      List<AdministrativeUnitDto> dtos = new ArrayList();
      Iterator var7 = parents.iterator();

      while(var7.hasNext()) {
         AdministrativeUnit cs = (AdministrativeUnit)var7.next();
         dtos.add(new AdministrativeUnitDto(cs));
      }

      Page<AdministrativeUnitDto> finalPage = new PageImpl(dtos, pageable, countElement);
      return finalPage;
   }

   public Page<AdministrativeUnitDto> findByPageBasicInfo(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.administrativeUnitRepository.findByPageBasicInfo(pageable);
   }

   public AdministrativeUnitDto deleteAdministrativeUnit(UUID id) {
      new ArrayList();
      AdministrativeUnitDto ret = new AdministrativeUnitDto();
      AdministrativeUnit au = (AdministrativeUnit)this.administrativeUnitRepository.getOne(id);
      if (au != null && au.getId() != null) {
         ret.setId(au.getId());
         ret.setCode(au.getCode());
         ret.setName(au.getName());
         List<AdministrativeUnit> list = this.administrativeUnitRepository.getListdministrativeUnitbyParent(au.getId());
         if (list == null || list.size() <= 0) {
            this.administrativeUnitRepository.deleteById(au.getId());
            ret.setCode("-1");
         }
      }

      return ret;
   }

   public AdministrativeUnit findByCode(String code) {
      List<AdministrativeUnit> list = this.administrativeUnitRepository.findListByCode(code);
      return list != null && list.size() > 0 ? (AdministrativeUnit)list.get(0) : null;
   }
}
