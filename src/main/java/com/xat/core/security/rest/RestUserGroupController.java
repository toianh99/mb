package com.xat.core.security.rest;

import com.xat.core.security.dto.UserGroupDto;
import com.xat.core.security.service.UserGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
   path = {"/api/usergroup"}
)
public class RestUserGroupController {
   @Autowired
   private UserGroupService service;

   @PreAuthorize("isAuthenticated()")
   @GetMapping(
      path = {"/{id}"}
   )
   public ResponseEntity<UserGroupDto> getUserGroup(@PathVariable Long id) {
      UserGroupDto dto = this.service.findById(id);
      return dto == null ? new ResponseEntity(new UserGroupDto(), HttpStatus.BAD_REQUEST) : new ResponseEntity(dto, HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping(
      path = {"/all"}
   )
   public ResponseEntity<List<UserGroupDto>> getUserGroups() {
      List<UserGroupDto> list = this.service.findAll();
      return new ResponseEntity(list, HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping(
      path = {"/list/{pageIndex}/{pageSize}"}
   )
   public ResponseEntity<Page<UserGroupDto>> getUserGroups(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
      if (pageIndex <= 0) {
         pageIndex = 0;
      }

      if (pageSize <= 0) {
         pageSize = 25;
      }

      Page<UserGroupDto> page = this.service.findList(pageIndex, pageSize);
      return new ResponseEntity(page, HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PostMapping
   public ResponseEntity<UserGroupDto> saveUser(@RequestBody UserGroupDto dto) {
      UserGroupDto _dto = this.service.save(dto);
      return _dto == null ? new ResponseEntity(new UserGroupDto(), HttpStatus.BAD_REQUEST) : new ResponseEntity(_dto, HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @DeleteMapping
   public void deleteUser(@RequestBody UserGroupDto[] dtos) {
      this.service.delete(dtos);
   }
}
