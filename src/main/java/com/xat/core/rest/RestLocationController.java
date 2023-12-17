package com.xat.core.rest;

import com.globits.core.domain.Location;
import com.globits.core.dto.LocationDto;
import com.globits.core.dto.ResultMessageDto;
import com.globits.core.service.LocationService;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@RequestMapping({"/api/location"})
public class RestLocationController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private LocationService locationService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<Location> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<Location> page = this.locationService.getList(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{locationId}"},
      method = {RequestMethod.GET}
   )
   public LocationDto getLocation(@PathVariable("locationId") UUID locationId) {
      return this.locationService.getById(locationId);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public LocationDto saveLocation(@RequestBody LocationDto location) {
      return this.locationService.saveLocation(location);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{locationId}"},
      method = {RequestMethod.PUT}
   )
   public LocationDto updateLocation(@RequestBody LocationDto Location, @PathVariable("locationId") Long locationId) {
      return this.locationService.saveLocation(Location);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{locationId}"},
      method = {RequestMethod.DELETE}
   )
   public ResponseEntity<ResultMessageDto> removeLocation(@PathVariable("locationId") UUID locationId) {
      ResultMessageDto dto = this.locationService.deleteLocation(locationId);
      return ResponseEntity.ok(dto);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/all"},
      method = {RequestMethod.GET}
   )
   public ResponseEntity<List<LocationDto>> getAllLocations() {
      List<LocationDto> list = this.locationService.getAll();
      return new ResponseEntity(list, HttpStatus.OK);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/checkCode/{code}"},
      method = {RequestMethod.GET}
   )
   public LocationDto checkDuplicateCode(@PathVariable("code") String code) {
      return this.locationService.checkDuplicateCode(code);
   }
}
