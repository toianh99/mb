package com.xat.core.service.impl;

import com.xat.core.domain.Country;
import com.xat.core.dto.CountryDto;
import com.xat.core.dto.ResultMessageDto;
import com.xat.core.repository.CountryRepository;
import com.xat.core.service.CountryService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CountryServiceImpl extends GenericServiceImpl<Country, UUID> implements CountryService {
   @Autowired
   CountryRepository countryRepository;

   public CountryDto checkDuplicateCode(String code) {
      CountryDto viewCheckDuplicateCodeDto = new CountryDto();
      Country country = null;
      List<Country> list = this.countryRepository.findListByCode(code);
      if (list != null && list.size() > 0) {
         country = (Country)list.get(0);
      }

      if (list == null) {
         viewCheckDuplicateCodeDto.setDuplicate(false);
      } else if (list != null && list.size() > 0) {
         viewCheckDuplicateCodeDto.setDuplicate(true);
         viewCheckDuplicateCodeDto.setDupCode(country.getCode());
         viewCheckDuplicateCodeDto.setDupName(country.getName());
      }

      return viewCheckDuplicateCodeDto;
   }

   public Country findByCode(String code) {
      List<Country> list = this.countryRepository.findListByCode(code);
      return list != null && list.size() > 0 ? (Country)list.get(0) : null;
   }

   public CountryDto getById(UUID id) {
      Country country = (Country)this.countryRepository.getOne(id);
      return new CountryDto(country);
   }

   public Page<CountryDto> getByPage(int pageIndex, int pageSize) {
      PageRequest pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.countryRepository.getByPage(pageable);
   }

   public CountryDto saveCountry(CountryDto dto) {
      Country country = null;
      if (dto != null && dto.getId() != null) {
         country = (Country)this.countryRepository.getOne(dto.getId());
      }

      if (country == null) {
         country = new Country();
         country.setUuidKey(UUID.randomUUID());
      }

      country.setCode(dto.getCode());
      country.setDescription(dto.getDescription());
      country.setName(dto.getName());
      country = (Country)this.countryRepository.save(country);
      return new CountryDto(country);
   }

   public ResultMessageDto deleteCountry(UUID id) {
      ResultMessageDto result = new ResultMessageDto();
      Country country = (Country)this.countryRepository.getOne(id);
      if (country == null) {
         result.setErrorCode(-1);
         result.setMessage("Không tìm thấy bản ghi để xóa");
      }

      try {
         this.countryRepository.delete(country);
         result.setErrorCode(0);
         result.setMessage("Xóa thành công");
      } catch (Exception var5) {
         result.setMessage("Có lỗi xảy ra trong quá trình xóa");
         result.setErrorCode(-2);
         var5.printStackTrace();
      }

      return result;
   }
}
