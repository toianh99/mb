package com.xat.core.service;

import com.globits.core.domain.Profession;
import com.globits.core.dto.ProfessionDto;
import java.util.UUID;

public interface ProfessionService extends GenericService<Profession, UUID> {
   ProfessionDto checkDuplicateCode(String code);

   ProfessionDto findProfessionById(UUID id);
}
