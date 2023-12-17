package com.xat.core.service.impl;

import com.xat.core.domain.Organization;
import com.xat.core.domain.OrganizationUser;
import com.xat.core.dto.OrganizationDto;
import com.xat.core.dto.OrganizationUserDto;
import com.xat.core.dto.ResultMessageDto;
import com.xat.core.repository.OrganizationRepository;
import com.xat.core.repository.OrganizationUserRepository;
import com.xat.core.service.OrganizationService;
import com.xat.core.security.domain.Role;
import com.xat.core.security.domain.User;
import com.xat.core.security.dto.RoleDto;
import com.xat.core.security.service.RoleService;
import com.xat.core.security.service.UserService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrganizationServiceImpl extends GenericServiceImpl<Organization, UUID> implements OrganizationService {
   @Autowired
   OrganizationRepository organizationRepository;
   @Autowired
   OrganizationUserRepository organizationUserRepository;
   @Autowired
   UserService userService;
   @Autowired
   RoleService roleService;

   public List<Organization> findOrganizationByUserId(Long userId) {
      return this.organizationRepository.findOrganizationByUserId(userId);
   }

   private List<Organization> getListSub(Organization dep, int level) {
      ArrayList<Organization> retList = new ArrayList();
      dep.setLevel(level);
      retList.add(dep);
      if (dep.getSubOrganizations() != null && dep.getSubOrganizations().size() > 0) {
         Iterator iter = dep.getSubOrganizations().iterator();

         while(iter.hasNext()) {
            Organization d = (Organization)iter.next();
            retList.addAll(this.getListSub(d, level + 1));
         }
      }

      return retList;
   }

   public Page<Organization> getListOrganizationByTree() {
      List<Organization> list = this.organizationRepository.getListOrganizationByTree();
      ArrayList<Organization> retList = new ArrayList();

      int pageSize;
      for(pageSize = 0; pageSize < list.size(); ++pageSize) {
         if (list.get(pageSize) != null) {
            List<Organization> childs = this.getListSub((Organization)list.get(pageSize), 0);
            if (childs != null && childs.size() > 0) {
               retList.addAll(childs);
            }
         }
      }

      pageSize = 1;
      if (retList.size() > 0) {
         pageSize = retList.size();
      }

      Pageable pageable = PageRequest.of(0, pageSize);
      Page<Organization> page = new PageImpl(retList, pageable, (long)pageSize);
      return page;
   }

   public Page<OrganizationDto> toDtoPage(Page<Organization> page) {
      Pageable pageable = PageRequest.of(page.getNumber(), page.getSize());
      List<OrganizationDto> ret = new ArrayList();
      if (page.getContent() != null && page.getContent().size() > 0) {
         Iterator var4 = page.getContent().iterator();

         while(var4.hasNext()) {
            Organization organization = (Organization)var4.next();
            OrganizationDto dto = new OrganizationDto(organization);
            ret.add(dto);
         }
      }

      return new PageImpl(ret, pageable, page.getTotalElements());
   }

   public OrganizationDto updateOrganization(Organization organization, UUID organizationId) {
      Organization updateOrganization = null;
      if (organization.getId() != null) {
         updateOrganization = (Organization)this.findById(organization.getId());
      } else {
         updateOrganization = (Organization)this.findById(organizationId);
      }

      updateOrganization.setCode(organization.getCode());
      updateOrganization.setName(organization.getName());
      updateOrganization.setOrganizationType(organization.getOrganizationType());
      if (organization.getParent() != null && organization.getParent().getId() != null) {
         Organization parentOrganization = (Organization)this.organizationRepository.getOne(organization.getParent().getId());
         if (parentOrganization.getId() != updateOrganization.getId()) {
            updateOrganization.setParent(parentOrganization);
         }
      } else if (updateOrganization.getParent() != null) {
         updateOrganization.setParent((Organization)null);
      }

      updateOrganization = (Organization)this.save(updateOrganization);
      if (updateOrganization.getParent() != null) {
         updateOrganization.setParent(new Organization(updateOrganization.getParent().getId(), updateOrganization.getParent().getName(), updateOrganization.getParent().getCode(), updateOrganization.getParent().getOrganizationType()));
      }

      return new OrganizationDto(updateOrganization);
   }

   public OrganizationDto saveOrganization(OrganizationDto organization) {
      Organization newOrganization = null;
      if (organization.getId() != null) {
         newOrganization = (Organization)this.organizationRepository.getOne(organization.getId());
      }

      if (newOrganization == null) {
         newOrganization = new Organization();
      }

      newOrganization.setCode(organization.getCode());
      newOrganization.setName(organization.getName());
      newOrganization.setLevel(organization.getLevel());
      newOrganization.setOrganizationType(organization.getOrganizationType());
      Organization parentOrganization;
      if (organization.getParent() != null && organization.getParent().getId() != null) {
         parentOrganization = (Organization)this.organizationRepository.getOne(organization.getParent().getId());
         if (parentOrganization.getId() != newOrganization.getId()) {
            newOrganization.setParent(parentOrganization);
         }
      } else {
         newOrganization.setParent((Organization)null);
      }

      parentOrganization = (Organization)this.save(newOrganization);
      OrganizationDto ret = new OrganizationDto(parentOrganization);
      if (organization.getUsers() != null) {
         for(int i = 0; i < organization.getUsers().size(); ++i) {
            OrganizationUserDto userDto = (OrganizationUserDto)organization.getUsers().get(i);
            userDto.setOrganization(ret);
            this.saveOrgnizationUser(userDto);
         }
      }

      return ret;
   }

   public ResultMessageDto deleteOrganizationUser(UUID id) {
      ResultMessageDto result = new ResultMessageDto();

      try {
         this.organizationUserRepository.deleteById(id);
         result.setErrorCode(0);
         result.setMessage("Xóa thành công Đơn vị mã số " + id.toString());
      } catch (Exception var4) {
         result.setErrorCode(-1);
         result.setMessage("Có lỗi trong quá trình xóa dữ liệu");
         var4.printStackTrace();
      }

      return result;
   }

   public ResultMessageDto deleteOrganization(UUID id) {
      ResultMessageDto result = new ResultMessageDto();

      try {
         this.organizationRepository.deleteById(id);
         result.setErrorCode(0);
         result.setMessage("Xóa thành công Đơn vị mã số " + id.toString());
      } catch (Exception var4) {
         result.setErrorCode(-1);
         result.setMessage("Có lỗi trong quá trình xóa dữ liệu");
         var4.printStackTrace();
      }

      return result;
   }

   public OrganizationUserDto saveOrgnizationUser(OrganizationUserDto orgUserDto) {
      OrganizationUser orgUser = null;
      if (orgUserDto.getId() != null) {
         orgUser = (OrganizationUser)this.organizationUserRepository.getOne(orgUserDto.getId());
      }

      if (orgUser == null) {
         orgUser = new OrganizationUser();
      }

      orgUser.setIsAdminUser(orgUserDto.getIsAdminUser());
      Organization organization = (Organization)this.organizationRepository.getOne(orgUserDto.getOrganization().getId());
      if (organization == null) {
         return null;
      } else {
         orgUser.setOrganization(organization);
         if ((orgUserDto.getUser().getRoles() == null || orgUserDto.getUser().getRoles().size() == 0) && orgUserDto.getIsAdminUser()) {
            Role orgAdminRole = this.roleService.findByName("ROLE_ORG_ADMIN");
            if (orgAdminRole != null) {
               orgUserDto.getUser().getRoles().add(new RoleDto(orgAdminRole));
            }
         }

         orgUserDto.getUser().setOrg(orgUserDto.getOrganization());
         User user = this.userService.saveUser(orgUserDto.getUser());
         orgUser.setUser(user);
         orgUser = (OrganizationUser)this.organizationUserRepository.save(orgUser);
         return new OrganizationUserDto(orgUser);
      }
   }

   public Page<OrganizationDto> getListPageList(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.organizationRepository.getBasicInfoByPage(pageable);
   }

   private List<OrganizationDto> createContentList(OrganizationDto dto) {
      List<OrganizationDto> list = new ArrayList();
      list.add(dto);
      if (dto.getSubDepartments() != null) {
         for(int i = 0; i < dto.getSubDepartments().size(); ++i) {
            ((OrganizationDto)dto.getSubDepartments().get(i)).setParentId(dto.getId());
            list.add((OrganizationDto)dto.getSubDepartments().get(i));
         }
      }

      return list;
   }

   public Page<OrganizationDto> getListRootTreeDataByPage(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      Page<OrganizationDto> page = this.organizationRepository.getFullInfoByPage(pageable);
      return page;
   }

   public Page<OrganizationDto> getListRootTreeDataByPage(String keyword, int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      keyword = "%" + keyword + "%";
      Page<OrganizationDto> page = this.organizationRepository.searchFullInfoByPage(keyword, pageable);
      return page;
   }

   public OrganizationDto getOrganization(UUID id) {
      Organization org = (Organization)this.repository.getOne(id);
      return org != null ? new OrganizationDto(org) : null;
   }
}
