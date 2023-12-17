package com.xat.core.service;

import com.xat.core.domain.Profession;
import com.xat.core.dto.ProfessionDto;
import java.util.UUID;

public interface ProfessionService extends GenericService<Profession, UUID> {
   ProfessionDto checkDuplicateCode(String code);

   ProfessionDto findProfessionById(UUID id);
}
