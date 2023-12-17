package com.xat.core.rest;

import com.globits.core.domain.Religion;
import com.globits.core.dto.ReligionDto;
import com.globits.core.service.ReligionService;
import java.util.UUID;
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
@RequestMapping({"/api/religion"})
public class RestReligionController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private ReligionService religionService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<Religion> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<Religion> page = this.religionService.getList(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{religionId}"},
      method = {RequestMethod.GET}
   )
   public Religion getReligion(@PathVariable("religionId") String religionId) {
      Religion religion = (Religion)this.religionService.findById(UUID.fromString(religionId));
      return religion;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public Religion saveReligion(@RequestBody Religion religion) {
      return (Religion)this.religionService.save(religion);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{religionId}"},
      method = {RequestMethod.PUT}
   )
   public Religion updateReligion(@RequestBody Religion Religion, @PathVariable("religionId") Long ReligionId) {
      return (Religion)this.religionService.save(Religion);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{religionId}"},
      method = {RequestMethod.DELETE}
   )
   public Religion removeReligion(@PathVariable("religionId") String religionId) {
      Religion Religion = (Religion)this.religionService.delete(UUID.fromString(religionId));
      return Religion;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/checkCode/{code}"},
      method = {RequestMethod.GET}
   )
   public ReligionDto checkDuplicateCode(@PathVariable("code") String code) {
      return this.religionService.checkDuplicateCode(code);
   }
}
