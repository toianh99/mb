package com.xat.core.rest;

import com.xat.core.domain.MenuItem;
import com.xat.core.dto.CommonSearchObjectDto;
import com.xat.core.dto.MenuItemDto;
import com.xat.core.dto.ResultMessageDto;
import com.xat.core.service.MenuItemService;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/menuitem"})
public class RestMenuItemController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private MenuItemService menuItemService;

   @RequestMapping(
      value = {"/getmenubyuser"},
      method = {RequestMethod.GET}
   )
   @PreAuthorize("isAuthenticated()")
   public List<MenuItemDto> getAllMenuItemByRoleList() {
      return this.menuItemService.getAllMenuItemByRoleList();
   }

   @Secured({"ROLE_SUPER_ADMIN"})
   @RequestMapping(
      value = {"searchByPage"},
      method = {RequestMethod.POST}
   )
   public Page<MenuItemDto> searchByPage(@RequestBody CommonSearchObjectDto searchObject) {
      Page<MenuItemDto> page = this.menuItemService.searchByPage(searchObject);
      return page;
   }

   @RequestMapping(
      value = {"/getall"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public List<MenuItemDto> getAll() {
      List<MenuItemDto> list = this.menuItemService.getAllMenuItem();
      return list;
   }

   @RequestMapping(
      value = {"/getallroot"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public List<MenuItemDto> getAllRoot() {
      List<MenuItemDto> list = this.menuItemService.getAllRootItem();
      return list;
   }

   @RequestMapping(
      value = {"/getflatrootchild"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public List<MenuItemDto> getFlatRootChild() {
      List<MenuItemDto> list = this.menuItemService.getFlatRootChild();
      return list;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{id}"},
      method = {RequestMethod.GET}
   )
   public MenuItemDto getMenuItem(@PathVariable("id") UUID id) {
      MenuItem menuItem = (MenuItem)this.menuItemService.findById(id);
      return new MenuItemDto(menuItem);
   }

   @Secured({"ROLE_SUPER_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public MenuItemDto saveMenuItem(@RequestBody MenuItemDto menuItem) {
      return this.menuItemService.saveMenuItem(menuItem);
   }

   @Secured({"ROLE_SUPER_ADMIN"})
   @RequestMapping(
      value = {"/{id}"},
      method = {RequestMethod.DELETE}
   )
   public ResponseEntity<ResultMessageDto> removeMenuItem(@PathVariable("id") UUID id) {
      ResultMessageDto result = this.menuItemService.deleteMenuItem(id);
      return new ResponseEntity(result, HttpStatus.OK);
   }
}
