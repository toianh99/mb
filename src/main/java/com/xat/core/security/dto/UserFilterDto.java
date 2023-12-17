package com.xat.core.security.dto;

public class UserFilterDto {
   private String keyword;
   private Boolean active;
   private RoleDto[] roles;
   private UserGroupDto[] groups;

   public String getKeyword() {
      return this.keyword;
   }

   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }

   public Boolean getActive() {
      return this.active;
   }

   public void setActive(Boolean active) {
      this.active = active;
   }

   public RoleDto[] getRoles() {
      return this.roles;
   }

   public void setRoles(RoleDto[] roles) {
      this.roles = roles;
   }

   public UserGroupDto[] getGroups() {
      return this.groups;
   }

   public void setGroups(UserGroupDto[] groups) {
      this.groups = groups;
   }
}
