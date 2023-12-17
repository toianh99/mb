package com.xat.core.service.impl;

import com.globits.core.domain.Profession;
import com.globits.core.dto.ProfessionDto;
import com.globits.core.repository.ProfessionRepository;
import com.globits.core.service.ProfessionService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProfessionServiceImpl extends GenericServiceImpl<Profession, UUID> implements ProfessionService {
   @Autowired
   ProfessionRepository professionRepository;

   public ProfessionDto checkDuplicateCode(String code) {
      ProfessionDto viewCheckDuplicateCodeDto = new ProfessionDto();
      Profession profession = null;
      List<Profession> list = this.professionRepository.findListByCode(code);
      if (list != null && list.size() > 0) {
         profession = (Profession)list.get(0);
      }

      if (list == null) {
         viewCheckDuplicateCodeDto.setDuplicate(false);
      } else if (list != null && list.size() > 0) {
         viewCheckDuplicateCodeDto.setDuplicate(true);
         viewCheckDuplicateCodeDto.setDupCode(profession.getCode());
         viewCheckDuplicateCodeDto.setDupName(profession.getName());
      }

      return viewCheckDuplicateCodeDto;
   }

   public ProfessionDto findProfessionById(UUID id) {
      Profession profession = (Profession)this.professionRepository.getOne(id);
      return profession != null ? new ProfessionDto(profession) : null;
   }
}
