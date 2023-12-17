package com.xat.core.service;

import com.globits.core.domain.Country;
import com.globits.core.dto.CountryDto;
import com.globits.core.dto.ResultMessageDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface CountryService extends GenericService<Country, UUID> {
   Page<CountryDto> getByPage(int pageIndex, int pageSize);

   CountryDto saveCountry(CountryDto dto);

   ResultMessageDto deleteCountry(UUID id);

   CountryDto checkDuplicateCode(String code);

   Country findByCode(String code);

   CountryDto getById(UUID id);
}
