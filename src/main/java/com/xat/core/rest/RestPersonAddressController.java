package com.xat.core.rest;

import com.xat.core.domain.PersonAddress;
import com.xat.core.service.PersonAddressService;
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
@RequestMapping({"/api/personaddress"})
public class RestPersonAddressController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private PersonAddressService personaddressService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_STUDENT_MANAGERMENT", "ROLE_STUDENT"})
   public Page<PersonAddress> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<PersonAddress> page = this.personaddressService.getList(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public PersonAddress savePersonAddress(@RequestBody PersonAddress personaddress) {
      return (PersonAddress)this.personaddressService.save(personaddress);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{personaddressId}"},
      method = {RequestMethod.PUT}
   )
   public PersonAddress updatePersonAddress(@RequestBody PersonAddress PersonAddress, @PathVariable("personaddressId") Long PersonAddressId) {
      return (PersonAddress)this.personaddressService.save(PersonAddress);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{personaddressId}"},
      method = {RequestMethod.DELETE}
   )
   public PersonAddress removePersonAddress(@PathVariable("personaddressId") String personaddressId) {
      PersonAddress PersonAddress = (PersonAddress)this.personaddressService.delete(UUID.fromString(personaddressId));
      return PersonAddress;
   }
}
