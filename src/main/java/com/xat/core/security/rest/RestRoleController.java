package com.globits.security.rest;

import com.globits.security.domain.Role;
import com.globits.security.dto.RoleDto;
import com.globits.security.service.RoleService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/roles"})
@Transactional
@Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
public class RestRoleController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private RoleService roleService;

   @RequestMapping
   public Role findByRoleName(@RequestParam String roleName) {
      return this.roleService.findByName(roleName);
   }

   @RequestMapping(
      value = {"/{roleId}"},
      method = {RequestMethod.GET}
   )
   public Role getRole(@PathVariable("roleId") String roleId) {
      return this.roleService.findById(new Long(roleId));
   }

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   public Page<Role> getRoles(@PathVariable int pageIndex, @PathVariable int pageSize) {
      return this.roleService.getList(pageIndex, pageSize);
   }

   @GetMapping(
      path = {"/all"}
   )
   public List<RoleDto> getAll() {
      return this.roleService.findAll();
   }

   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public RoleDto createRole(@RequestBody RoleDto roleDto) {
      return this.roleService.createRole(roleDto);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{roleId}"},
      method = {RequestMethod.PUT}
   )
   public RoleDto updateRole(@RequestBody RoleDto roleDto, @PathVariable("roleId") Long roleId) {
      return this.roleService.updateRole(roleDto, roleId);
   }

   @RequestMapping(
      value = {"/{roleId}"},
      method = {RequestMethod.DELETE}
   )
   public Role removeRole(@PathVariable("roleId") String roleId) {
      return (Role)this.roleService.delete(new Long(roleId));
   }
}
