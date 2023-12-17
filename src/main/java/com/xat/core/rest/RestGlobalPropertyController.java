package com.xat.core.rest;

import com.globits.core.domain.GlobalProperty;
import com.globits.core.service.GlobalPropertyService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/globalproperty"})
public class RestGlobalPropertyController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private GlobalPropertyService globalPropertyService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_STUDENT_MANAGERMENT"})
   public Page<GlobalProperty> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<GlobalProperty> page = this.globalPropertyService.findByPage(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public GlobalProperty saveGlobalProperty(@RequestBody GlobalProperty country) {
      return this.globalPropertyService.save(country);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{property}"},
      method = {RequestMethod.PUT}
   )
   public GlobalProperty updateGlobalProperty(@RequestBody GlobalProperty global, @PathVariable("property") String property) {
      return this.globalPropertyService.updateGlobalProperty(global);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{property}"},
      method = {RequestMethod.DELETE}
   )
   public GlobalProperty removeGlobalProperty(@PathVariable("property") String property) {
      GlobalProperty global = this.globalPropertyService.delete(property);
      return global;
   }
}
