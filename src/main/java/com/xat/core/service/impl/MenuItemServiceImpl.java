package com.xat.core.service.impl;

import com.globits.core.Constants;
import com.globits.core.domain.MenuItem;
import com.globits.core.domain.MenuItemRole;
import com.globits.core.dto.CommonSearchObjectDto;
import com.globits.core.dto.MenuItemDto;
import com.globits.core.dto.ResultMessageDto;
import com.globits.core.repository.MenuItemRepository;
import com.globits.core.repository.MenuItemRoleRepository;
import com.globits.core.service.ActivityLogService;
import com.globits.core.service.MenuItemService;
import com.globits.core.utils.SecurityUtils;
import com.globits.security.domain.Role;
import com.globits.security.domain.User;
import com.globits.security.dto.RoleDto;
import com.globits.security.repository.RoleRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MenuItemServiceImpl extends GenericServiceImpl<MenuItem, UUID> implements MenuItemService {
   @Autowired
   MenuItemRepository menuItemRepository;
   @Autowired
   MenuItemRoleRepository menuItemRoleRepository;
   @Autowired
   ActivityLogService activityLogService;
   @Autowired
   RoleRepository roleRepository;

   public MenuItemDto saveMenuItem(MenuItemDto dto) {
      MenuItem menuItem = null;
      if (dto.getId() != null) {
         menuItem = (MenuItem)this.menuItemRepository.getOne(dto.getId());
      }

      if (menuItem == null) {
         menuItem = new MenuItem();
      }

      menuItem.setCode(dto.getCode());
      menuItem.setName(dto.getName());
      menuItem.setPath(dto.getPath());
      menuItem.setIcon(dto.getIcon());
      menuItem.setViewIndex(dto.getViewIndex());
      menuItem.setDescription(dto.getDescription());
      if (dto.getParent() != null && dto.getParent().getId() != null) {
         MenuItem parent = (MenuItem)this.menuItemRepository.getOne(dto.getParent().getId());
         menuItem.setParent(parent);
      }

      if (dto.getRoles() != null) {
         HashSet<MenuItemRole> menuItemRoles = new HashSet();
         Iterator var4 = dto.getRoles().iterator();

         while(var4.hasNext()) {
            RoleDto roleDto = (RoleDto)var4.next();
            MenuItemRole itemRole = null;
            if (roleDto.getId() != null && menuItem.getId() != null) {
               itemRole = this.menuItemRoleRepository.findByRoleMenuId(roleDto.getId(), menuItem.getId());
            }

            if (itemRole != null) {
               menuItemRoles.add(itemRole);
            } else if (roleDto.getId() != null) {
               itemRole = new MenuItemRole();
               Role role = (Role)this.roleRepository.getOne(roleDto.getId());
               if (role != null) {
                  itemRole.setRole(role);
               }

               itemRole.setRole(role);
               itemRole.setMenuItem(menuItem);
               menuItemRoles.add(itemRole);
            }
         }

         if (menuItem.getRoles() == null) {
            menuItem.setRoles(new HashSet());
         }

         menuItem.getRoles().clear();
         menuItem.getRoles().addAll(menuItemRoles);
      }

      menuItem = (MenuItem)this.menuItemRepository.save(menuItem);
      this.activityLogService.createActivityLogByEntity(menuItem, Constants.ActionLogTypeEnum.SaveOrUpdate.getValue());
      return new MenuItemDto(menuItem);
   }

   public List<MenuItemDto> getAllMenuItem() {
      return this.menuItemRepository.getAllMenuItem();
   }

   private List<MenuItemDto> removeNonePermissionMenu(List<MenuItemDto> menus, List<Long> roleIds) {
      List<MenuItemDto> ret = new ArrayList();
      Iterator var4 = menus.iterator();

      while(var4.hasNext()) {
         MenuItemDto m = (MenuItemDto)var4.next();
         Boolean isOK = false;
         if (m.getRoles() != null) {
            Iterator var7 = m.getRoles().iterator();

            while(var7.hasNext()) {
               RoleDto r = (RoleDto)var7.next();
               Iterator var9 = roleIds.iterator();

               while(var9.hasNext()) {
                  Long i = (Long)var9.next();
                  if (r.getId().equals(i)) {
                     isOK = true;
                  }
               }
            }
         }

         if (isOK) {
            if (m.getChildren() != null) {
               List<MenuItemDto> chidren = this.removeNonePermissionMenu(m.getChildren(), roleIds);
               m.setChildren(chidren);
            }

            ret.add(m);
         }
      }

      return ret;
   }

   public List<MenuItemDto> getAllMenuItemByRoleList() {
      ArrayList<Long> roleIds = new ArrayList();
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
         User modifiedUser = (User)authentication.getPrincipal();
         Boolean isAdmin = SecurityUtils.isUserInRole(modifiedUser, "ROLE_SUPER_ADMIN");
         if (isAdmin) {
            return this.menuItemRepository.getAllRootItem();
         }

         Iterator var5 = modifiedUser.getRoles().iterator();

         while(var5.hasNext()) {
            Role r = (Role)var5.next();
            roleIds.add(r.getId());
         }
      }

      List<MenuItemDto> ret = this.menuItemRepository.getAllMenuItemByRoleList(roleIds);
      ret = this.removeNonePermissionMenu(ret, roleIds);
      return ret;
   }

   public List<MenuItemDto> getAllRootItem() {
      return this.menuItemRepository.getAllRootItem();
   }

   public List<MenuItemDto> getFlatRootChild() {
      List<MenuItem> list = this.menuItemRepository.getAllRootEntity();
      List<MenuItemDto> result = new ArrayList();
      if (list != null) {
         Iterator var3 = list.iterator();

         while(var3.hasNext()) {
            MenuItem entity = (MenuItem)var3.next();
            MenuItemDto dto = new MenuItemDto(entity, false);
            result.add(dto);
         }
      }

      return result;
   }

   public Page<MenuItemDto> searchByPage(CommonSearchObjectDto searchObject) {
      Pageable pageable = PageRequest.of(searchObject.getPageIndex() - 1, searchObject.getPageSize());
      String keyword = "%" + searchObject.getKeyword() + "%";
      return this.menuItemRepository.searchByPage(keyword, pageable);
   }

   public ResultMessageDto deleteMenuItem(UUID id) {
      ResultMessageDto result = new ResultMessageDto();
      result.setErrorCode(0);
      result.setMessage("general.success");
      this.menuItemRepository.deleteById(id);
      return result;
   }
}
