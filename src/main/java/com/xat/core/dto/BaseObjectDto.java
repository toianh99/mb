package com.xat.core.dto;

import com.xat.core.domain.BaseObject;
import java.util.UUID;

public class BaseObjectDto extends AuditableEntityDto {
   protected UUID id;
   protected boolean voided;

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public boolean isVoided() {
      return this.voided;
   }

   public void setVoided(boolean voided) {
      this.voided = voided;
   }

   public BaseObjectDto() {
   }

   public BaseObjectDto(BaseObject entity) {
      super(entity);
      if (entity != null) {
         this.id = entity.getId();
         if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
         }
      }

   }
}
