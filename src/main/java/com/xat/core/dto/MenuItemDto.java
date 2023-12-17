package com.xat.core.dto;
import com.xat.core.domain.MenuItem;
import com.xat.core.domain.MenuItemRole;
import com.xat.core.security.dto.RoleDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuItemDto extends BaseObjectDto {
   private String name;
   private String code;
   private String description;
   private String path;
   private String icon;
   private Integer viewIndex;
   private Boolean isVisible;
   private MenuItemDto parent;
   private List<MenuItemDto> children;
   private List<RoleDto> roles;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public String getIcon() {
      return this.icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getViewIndex() {
      return this.viewIndex;
   }

   public void setViewIndex(Integer viewIndex) {
      this.viewIndex = viewIndex;
   }

   public MenuItemDto getParent() {
      return this.parent;
   }

   public void setParent(MenuItemDto parent) {
      this.parent = parent;
   }

   public String getParentId() {
      return this.parent != null ? this.parent.id.toString() : null;
   }

   public Boolean getIsVisible() {
      return this.isVisible;
   }

   public void setIsVisible(Boolean isVisible) {
      this.isVisible = isVisible;
   }

   public List<MenuItemDto> getChildren() {
      return this.children;
   }

   public void setChildren(List<MenuItemDto> children) {
      this.children = children;
   }

   public List<RoleDto> getRoles() {
      return this.roles;
   }

   public void setRoles(List<RoleDto> roles) {
      this.roles = roles;
   }

   public MenuItemDto() {
      this.isVisible = true;
   }

   public MenuItemDto(MenuItem entity) {
      this(entity, true);
   }

   public MenuItemDto(MenuItem entity, Boolean isGetParent) {
      this.isVisible = true;
      this.setId(entity.getId());
      this.setCode(entity.getCode());
      this.setName(entity.getName());
      this.setPath(entity.getPath());
      this.setIcon(entity.getIcon());
      this.setViewIndex(entity.getViewIndex());
      this.setDescription(entity.getDescription());
      if (isGetParent && entity.getParent() != null) {
         this.parent = new MenuItemDto();
         this.parent.setCode(entity.getParent().getCode());
         this.parent.setName(entity.getParent().getName());
         this.parent.setId(entity.getParent().getId());
         this.parent.setPath(entity.getParent().getPath());
         this.parent.setIcon(entity.getParent().getIcon());
         this.parent.setViewIndex(entity.getParent().getViewIndex());
         this.parent.setDescription(entity.getParent().getDescription());
      }

      Iterator var3;
      if (entity.getChildren() != null) {
         this.children = new ArrayList();
         var3 = entity.getChildren().iterator();

         while(var3.hasNext()) {
            MenuItem child = (MenuItem)var3.next();
            this.children.add(new MenuItemDto(child, true));
         }
      }

      if (entity.getRoles() != null) {
         this.roles = new ArrayList();

         RoleDto roleDto;
         for(var3 = entity.getRoles().iterator(); var3.hasNext(); this.roles.add(roleDto)) {
            MenuItemRole role = (MenuItemRole)var3.next();
            roleDto = new RoleDto();
            if (role.getRole() != null) {
               roleDto.setId(role.getRole().getId());
               roleDto.setDescription(role.getRole().getDescription());
               roleDto.setName(role.getRole().getName());
            }
         }
      }

   }
}
