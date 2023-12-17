package com.xat.core.rest;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.dto.AdministrativeUnitDto;
import com.globits.core.service.AdministrativeUnitService;
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
@RequestMapping({"/api/administrativeunit"})
public class RestAdministrativeUnitController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private AdministrativeUnitService administrativeUnitService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   public Page<AdministrativeUnit> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<AdministrativeUnit> page = this.administrativeUnitService.getListAdministrativeUnitByTree(pageIndex, pageSize);
      return page;
   }

   @RequestMapping(
      value = {"tree/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   public ResponseEntity<Page<AdministrativeUnitDto>> getListTree(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<AdministrativeUnitDto> page = this.administrativeUnitService.getListTree(pageIndex, pageSize);
      return new ResponseEntity(page, HttpStatus.OK);
   }

   @RequestMapping(
      value = {"/listProvince/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   public Page<AdministrativeUnit> getListProvince(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<AdministrativeUnit> page = this.administrativeUnitService.getPageRootAdministrativeUnit(pageIndex, pageSize);
      return page;
   }

   @RequestMapping(
      value = {"/list_province"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   public List<AdministrativeUnitDto> getListProvince() {
      List<AdministrativeUnitDto> list = this.administrativeUnitService.getListRootAdministrativeUnitDto();
      return list;
   }

   @RequestMapping(
      value = {"/listCity/{parentId}/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   public Page<AdministrativeUnit> getListCity(@PathVariable("parentId") UUID parentId, @PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<AdministrativeUnit> page = this.administrativeUnitService.getPageByParentId(parentId, pageIndex, pageSize);
      return page;
   }

   @RequestMapping(
      value = {"/get_list_by_parent_id/{parentId}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   public List<AdministrativeUnitDto> getListDtoByParentId(@PathVariable("parentId") UUID parentId) {
      List<AdministrativeUnitDto> page = this.administrativeUnitService.getListDtoByParentId(parentId);
      return page;
   }

   @RequestMapping(
      value = {"/simple/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   public Page<AdministrativeUnitDto> findByPageBasicInfo(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<AdministrativeUnitDto> page = this.administrativeUnitService.findByPageBasicInfo(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{administrativeUnitId}"},
      method = {RequestMethod.GET}
   )
   public AdministrativeUnit geAdministrativeUnit(@PathVariable("administrativeUnitId") String administrativeUnitId) {
      AdministrativeUnit administrativeUnit = (AdministrativeUnit)this.administrativeUnitService.findById(UUID.fromString(administrativeUnitId));
      administrativeUnit = new AdministrativeUnit(administrativeUnit, true);
      return administrativeUnit;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public AdministrativeUnitDto saveAdministrativeUnit(@RequestBody AdministrativeUnitDto AdministrativeUnit) {
      return this.administrativeUnitService.saveAdministrativeUnit(AdministrativeUnit);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{administrativeUnitId}"},
      method = {RequestMethod.PUT}
   )
   public AdministrativeUnit updateAdministrativeUnit(@RequestBody AdministrativeUnit AdministrativeUnit, @PathVariable("administrativeUnitId") String administrativeUnitId) {
      return this.administrativeUnitService.updateAdministrativeUnit(AdministrativeUnit, UUID.fromString(administrativeUnitId));
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{administrativeUnitId}"},
      method = {RequestMethod.DELETE}
   )
   public AdministrativeUnitDto removeAdministrativeUnit(@PathVariable("administrativeUnitId") String administrativeUnitId) {
      AdministrativeUnitDto administrativeUnit = this.administrativeUnitService.deleteAdministrativeUnit(UUID.fromString(administrativeUnitId));
      return administrativeUnit;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.DELETE}
   )
   public int removeAdministrativeUnits(@RequestBody List<AdministrativeUnitDto> dtos) {
      return this.administrativeUnitService.deleteAdministrativeUnits(dtos);
   }
}
