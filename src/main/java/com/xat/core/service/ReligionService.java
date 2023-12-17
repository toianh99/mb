package com.xat.core.service;

import com.globits.core.domain.Religion;
import com.globits.core.dto.ReligionDto;
import java.util.UUID;

public interface ReligionService extends GenericService<Religion, UUID> {
   ReligionDto checkDuplicateCode(String code);

   Religion findByCode(String code);
}
