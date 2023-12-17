package com.xat.core.service.impl;

import com.xat.core.domain.Ethnics;
import com.xat.core.dto.EthnicsDto;
import com.xat.core.repository.EthnicsRepository;
import com.xat.core.service.EthnicsService;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EthnicsServiceImpl extends GenericServiceImpl<Ethnics, UUID> implements EthnicsService {
   @Autowired
   EthnicsRepository ethnicsRepository;

   public int deleteEthnicss(List<EthnicsDto> dtos) {
      if (dtos == null) {
         return 0;
      } else {
         int ret = 0;

         for(Iterator var3 = dtos.iterator(); var3.hasNext(); ++ret) {
            EthnicsDto dto = (EthnicsDto)var3.next();
            if (dto.getId() != null) {
               this.ethnicsRepository.deleteById(dto.getId());
            }
         }

         return ret;
      }
   }

   public EthnicsDto checkDuplicateCode(String code) {
      EthnicsDto viewCheckDuplicateCodeDto = new EthnicsDto();
      Ethnics ethnics = null;
      List<Ethnics> list = this.ethnicsRepository.findListByCode(code);
      if (list != null && list.size() > 0) {
         ethnics = (Ethnics)list.get(0);
      }

      if (list == null) {
         viewCheckDuplicateCodeDto.setDuplicate(false);
      } else if (list != null && list.size() > 0) {
         viewCheckDuplicateCodeDto.setDuplicate(true);
         viewCheckDuplicateCodeDto.setDupCode(ethnics.getCode());
         viewCheckDuplicateCodeDto.setDupName(ethnics.getName());
      }

      return viewCheckDuplicateCodeDto;
   }

   public Ethnics findByCode(String code) {
      List<Ethnics> list = this.ethnicsRepository.findListByCode(code);
      return list != null && list.size() > 0 ? (Ethnics)list.get(0) : null;
   }

   public EthnicsDto findEthnicsById(UUID id) {
      Ethnics ethnics = (Ethnics)this.ethnicsRepository.getOne(id);
      return ethnics != null ? new EthnicsDto(ethnics) : null;
   }

   public EthnicsDto saveEthnics(EthnicsDto ethnics) {
      Ethnics updateEthnics = (Ethnics)this.ethnicsRepository.getOne(ethnics.getId());
      if (updateEthnics == null) {
         updateEthnics = new Ethnics();
      }

      updateEthnics.setCode(ethnics.getCode());
      updateEthnics.setDescription(ethnics.getDescription());
      updateEthnics.setName(ethnics.getName());
      updateEthnics.setUuidKey(updateEthnics.getId());
      updateEthnics = (Ethnics)this.ethnicsRepository.save(updateEthnics);
      return new EthnicsDto(updateEthnics);
   }
}
