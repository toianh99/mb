package com.xat.core.security.dto;

import com.xat.core.dto.AuditableEntityDto;
import com.xat.core.security.domain.UserGroup;

public class UserGroupDto extends AuditableEntityDto {
   private Long id;
   private String name;
   private String description;

   public UserGroupDto() {
   }

   public UserGroupDto(UserGroup entity) {
      if (entity != null) {
         this.id = entity.getId();
         this.name = entity.getName();
         this.description = entity.getDescription();
         this.setCreateDate(entity.getCreateDate());
         this.setCreatedBy(entity.getCreatedBy());
         this.setModifyDate(entity.getModifyDate());
         this.setModifiedBy(entity.getModifiedBy());
      }
   }

   public UserGroup toEntity() {
      UserGroup entity = new UserGroup();
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
}
