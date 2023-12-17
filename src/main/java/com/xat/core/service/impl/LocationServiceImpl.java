package com.xat.core.service.impl;

import com.xat.core.domain.Location;
import com.xat.core.dto.LocationDto;
import com.xat.core.dto.ResultMessageDto;
import com.xat.core.repository.LocationRepository;
import com.xat.core.service.LocationService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LocationServiceImpl extends GenericServiceImpl<Location, UUID> implements LocationService {
   @Autowired
   LocationRepository locationRepository;

   public List<LocationDto> getAll() {
      Iterator<Location> itr = this.repository.findAll().iterator();
      ArrayList list = new ArrayList();

      while(itr.hasNext()) {
         list.add(new LocationDto((Location)itr.next()));
      }

      return list;
   }

   public LocationDto checkDuplicateCode(String code) {
      LocationDto viewCheckDuplicateCodeDto = new LocationDto();
      Location location = null;
      List<Location> list = this.locationRepository.findByCode(code);
      if (list != null && list.size() > 0) {
         location = (Location)list.get(0);
      }

      if (list == null) {
         viewCheckDuplicateCodeDto.setDuplicate(false);
      } else if (list != null && list.size() > 0) {
         viewCheckDuplicateCodeDto.setDuplicate(true);
         viewCheckDuplicateCodeDto.setDupCode(location.getCode());
         viewCheckDuplicateCodeDto.setDupName(location.getName());
      }

      return viewCheckDuplicateCodeDto;
   }

   public Page<LocationDto> getByPage(int pageIndex, int pageSize) {
      PageRequest pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.locationRepository.getByPage(pageable);
   }

   public LocationDto saveLocation(LocationDto dto) {
      Location location = null;
      if (dto != null && dto.getId() != null) {
         location = (Location)this.locationRepository.getOne(dto.getId());
      }

      if (location == null) {
         location = new Location();
         location.setUuidKey(UUID.randomUUID());
      }

      location.setCode(dto.getCode());
      location.setLatitude(dto.getLatitude());
      location.setLongitude(dto.getLongitude());
      location.setName(dto.getName());
      location = (Location)this.locationRepository.save(location);
      return new LocationDto(location);
   }

   public ResultMessageDto deleteLocation(UUID id) {
      ResultMessageDto result = new ResultMessageDto();
      Location location = (Location)this.locationRepository.getOne(id);
      if (location == null) {
         result.setErrorCode(-1);
         result.setMessage("Không tìm thấy bản ghi để xóa");
      }

      try {
         this.locationRepository.delete(location);
         result.setErrorCode(0);
         result.setMessage("Xóa thành công");
      } catch (Exception var5) {
         result.setMessage("Có lỗi xảy ra trong quá trình xóa");
         result.setErrorCode(-2);
         var5.printStackTrace();
      }

      return result;
   }

   public LocationDto getById(UUID id) {
      Location entity = (Location)this.locationRepository.getOne(id);
      return entity != null ? new LocationDto(entity) : null;
   }
}
