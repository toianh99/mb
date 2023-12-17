package com.globits.security.dto;

import com.globits.security.domain.Role;
import java.io.Serializable;

public class RoleDto implements Serializable {
   private static final long serialVersionUID = 2472975613567232512L;
   private Long id;
   private String name;
   private String description;

   public RoleDto() {
   }

   public RoleDto(Role entity) {
      if (entity != null) {
         this.id = entity.getId();
         this.name = entity.getName();
         this.description = entity.getDescription();
      }

   }

   public Role toEntity() {
      Role entity = new Role();
      entity.setId(this.id);
      entity.setName(this.name);
      entity.setDescription(this.description);
      return entity;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getAuthority() {
      return this.name;
   }
}
