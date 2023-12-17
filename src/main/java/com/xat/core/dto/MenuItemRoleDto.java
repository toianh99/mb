package com.xat.core.dto;


import com.xat.core.domain.MenuItemRole;
import com.xat.core.security.dto.RoleDto;

public class MenuItemRoleDto extends BaseObjectDto {
   private MenuItemDto menuItem;
   private RoleDto role;

   public MenuItemDto getMenuItem() {
      return this.menuItem;
   }

   public void setMenuItem(MenuItemDto menuItem) {
      this.menuItem = menuItem;
   }

   public RoleDto getRole() {
      return this.role;
   }

   public void setRole(RoleDto role) {
      this.role = role;
   }

   public MenuItemRoleDto() {
   }

   public MenuItemRoleDto(MenuItemRole entity) {
      this.setId(entity.getId());
      if (entity.getRole() != null) {
         this.role = new RoleDto();
         this.role.setName(entity.getRole().getName());
         this.role.setDescription(entity.getRole().getDescription());
         this.role.setId(entity.getRole().getId());
      }

      if (entity.getMenuItem() != null) {
         this.menuItem = new MenuItemDto();
         this.menuItem.setId(this.getMenuItem().getId());
         this.menuItem.setName(this.getMenuItem().getName());
         this.menuItem.setCode(this.getMenuItem().getCode());
         this.menuItem.setIcon(this.getMenuItem().getIcon());
         this.menuItem.setPath(this.getMenuItem().getPath());
      }

   }
}
