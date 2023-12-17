package com.xat.core.rest;

import com.xat.core.domain.Country;
import com.xat.core.dto.CountryDto;
import com.xat.core.dto.ResultMessageDto;
import com.xat.core.service.CountryService;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/country"})
public class RestCountryController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private CountryService countryService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<CountryDto> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<CountryDto> page = this.countryService.getByPage(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/checkCode/{code}"},
      method = {RequestMethod.GET}
   )
   public CountryDto checkDuplicateCode(@PathVariable("code") String code) {
      return this.countryService.checkDuplicateCode(code);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{countryId}"},
      method = {RequestMethod.GET}
   )
   public CountryDto getCountry(@PathVariable("countryId") UUID countryId) {
      Country country = (Country)this.countryService.findById(countryId);
      return new CountryDto(country);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public CountryDto saveCountry(@RequestBody CountryDto country) {
      return this.countryService.saveCountry(country);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{countryId}"},
      method = {RequestMethod.DELETE}
   )
   public ResponseEntity<ResultMessageDto> removeCountry(@PathVariable("id") UUID id) {
      ResultMessageDto result = this.countryService.deleteCountry(id);
      return new ResponseEntity(result, HttpStatus.OK);
   }
}
