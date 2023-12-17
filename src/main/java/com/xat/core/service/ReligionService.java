package com.xat.core.service;

import com.xat.core.domain.Religion;
import com.xat.core.dto.ReligionDto;
import java.util.UUID;

public interface ReligionService extends GenericService<Religion, UUID> {
   ReligionDto checkDuplicateCode(String code);

   Religion findByCode(String code);
}
