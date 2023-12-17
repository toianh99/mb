package com.xat.core.rest;

import com.xat.core.domain.Ethnics;
import com.xat.core.dto.EthnicsDto;
import com.xat.core.service.EthnicsService;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/ethnics"})
public class RestEthnicsController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private EthnicsService ethnicsService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<Ethnics> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<Ethnics> page = this.ethnicsService.getList(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{ethnicsId}"},
      method = {RequestMethod.GET}
   )
   public EthnicsDto getEthnics(@PathVariable("ethnicsId") String ethnicsId) {
      return this.ethnicsService.findEthnicsById(UUID.fromString(ethnicsId));
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public EthnicsDto saveEthnics(@RequestBody EthnicsDto ethnics) {
      return this.ethnicsService.saveEthnics(ethnics);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{ethnicsId}"},
      method = {RequestMethod.PUT}
   )
   public Ethnics updateEthnics(@RequestBody Ethnics ethnics, @PathVariable("ethnicsId") Long ethnicsId) {
      return (Ethnics)this.ethnicsService.save(ethnics);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{ethnicsId}"},
      method = {RequestMethod.DELETE}
   )
   public Ethnics removeEthnics(@PathVariable("ethnicsId") String ethnicsId) {
      Ethnics ethnics = (Ethnics)this.ethnicsService.delete(UUID.fromString(ethnicsId));
      return ethnics;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/checkCode/{code}"},
      method = {RequestMethod.GET}
   )
   public EthnicsDto checkDuplicateCode(@PathVariable("code") String code) {
      return this.ethnicsService.checkDuplicateCode(code);
   }
}
