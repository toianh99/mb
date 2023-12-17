package com.xat.core.service;

import com.globits.core.domain.Location;
import com.globits.core.dto.LocationDto;
import com.globits.core.dto.ResultMessageDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface LocationService extends GenericService<Location, UUID> {
   List<LocationDto> getAll();

   LocationDto checkDuplicateCode(String code);

   Page<LocationDto> getByPage(int pageIndex, int pageSize);

   LocationDto saveLocation(LocationDto dto);

   LocationDto getById(UUID id);

   ResultMessageDto deleteLocation(UUID id);
}
