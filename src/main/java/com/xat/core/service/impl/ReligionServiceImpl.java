package com.xat.core.service.impl;

import com.globits.core.domain.Religion;
import com.globits.core.dto.ReligionDto;
import com.globits.core.repository.ReligionRepository;
import com.globits.core.service.ReligionService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ReligionServiceImpl extends GenericServiceImpl<Religion, UUID> implements ReligionService {
   @Autowired
   ReligionRepository religionRepository;

   public ReligionDto checkDuplicateCode(String code) {
      ReligionDto viewCheckDuplicateCodeDto = new ReligionDto();
      Religion department = null;
      List<Religion> list = this.religionRepository.findListByCode(code);
      if (list != null && list.size() > 0) {
         department = (Religion)list.get(0);
      }

      if (list == null) {
         viewCheckDuplicateCodeDto.setDuplicate(false);
      } else if (list != null && list.size() > 0) {
         viewCheckDuplicateCodeDto.setDuplicate(true);
         viewCheckDuplicateCodeDto.setDupCode(department.getCode());
         viewCheckDuplicateCodeDto.setDupName(department.getName());
      }

      return viewCheckDuplicateCodeDto;
   }

   public Religion findByCode(String code) {
      List<Religion> list = this.religionRepository.findListByCode(code);
      return list != null && list.size() > 0 ? (Religion)list.get(0) : null;
   }
}
