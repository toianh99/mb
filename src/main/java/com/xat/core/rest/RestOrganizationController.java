package com.xat.core.rest;

import com.xat.core.domain.Organization;
import com.xat.core.dto.OrganizationDto;
import com.xat.core.dto.OrganizationUserDto;
import com.xat.core.dto.ResultMessageDto;
import com.xat.core.dto.SimpleSearchModelDto;
import com.xat.core.service.OrganizationService;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/organization"})
public class RestOrganizationController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private OrganizationService organizationService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_STUDENT_MANAGERMENT", "ROLE_STUDENT"})
   public Page<OrganizationDto> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<OrganizationDto> page = this.organizationService.getListPageList(pageIndex, pageSize);
      return page;
   }

   @RequestMapping(
      value = {"/tree/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<Organization> getListTree(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<Organization> page = this.organizationService.getListOrganizationByTree();
      return page;
   }

   @RequestMapping(
      value = {"/tree/full/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<OrganizationDto> getListRootTreeDataByPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
      return this.organizationService.getListRootTreeDataByPage(pageIndex, pageSize);
   }

   @RequestMapping(
      value = {"/tree/full/{pageIndex}/{pageSize}"},
      method = {RequestMethod.POST}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<OrganizationDto> getListRootTreeDataByPage(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody SimpleSearchModelDto searchModel) {
      return searchModel != null ? this.organizationService.getListRootTreeDataByPage(searchModel.getName(), pageIndex, pageSize) : this.organizationService.getListRootTreeDataByPage(pageIndex, pageSize);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_STUDENT_MANAGERMENT", "ROLE_STUDENT"})
   @RequestMapping(
      value = {"/{organizationId}"},
      method = {RequestMethod.GET}
   )
   public OrganizationDto getOrganization(@PathVariable("organizationId") UUID organizationId) {
      OrganizationDto orga = this.organizationService.getOrganization(organizationId);
      return orga;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public OrganizationDto saveOrganization(@RequestBody OrganizationDto organization) {
      return this.organizationService.saveOrganization(organization);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/saveorganizationuser"},
      method = {RequestMethod.POST}
   )
   public OrganizationUserDto saveOrgnizationUser(@RequestBody OrganizationUserDto orgUserDto) {
      return this.organizationService.saveOrgnizationUser(orgUserDto);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/organizationuser/{id}"},
      method = {RequestMethod.DELETE}
   )
   public ResponseEntity<ResultMessageDto> deleteOrgnizationUser(@PathVariable UUID id) {
      ResultMessageDto dto = this.organizationService.deleteOrganizationUser(id);
      return ResponseEntity.ok(dto);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{organizationId}"},
      method = {RequestMethod.PUT}
   )
   public OrganizationDto updateOrganization(@RequestBody Organization organization, @PathVariable("organizationId") UUID organizationId) {
      return this.organizationService.updateOrganization(organization, organizationId);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{organizationId}"},
      method = {RequestMethod.DELETE}
   )
   public ResponseEntity<ResultMessageDto> removeOrganization(@PathVariable UUID organizationId) {
      ResultMessageDto dto = this.organizationService.deleteOrganization(organizationId);
      return ResponseEntity.ok(dto);
   }
}
