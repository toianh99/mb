package com.xat.core.dto;

import com.xat.core.auditing.AuditableEntity;
import org.joda.time.LocalDateTime;

public class AuditableEntityDto {
   protected LocalDateTime createDate;
   protected String createdBy;
   protected LocalDateTime modifyDate;
   protected String modifiedBy;

   public LocalDateTime getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(LocalDateTime createDate) {
      this.createDate = createDate;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }

   public LocalDateTime getModifyDate() {
      return this.modifyDate;
   }

   public void setModifyDate(LocalDateTime modifyDate) {
      this.modifyDate = modifyDate;
   }

   public String getModifiedBy() {
      return this.modifiedBy;
   }

   public void setModifiedBy(String modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public AuditableEntityDto() {
   }

   public AuditableEntityDto(AuditableEntity entity) {
      if (entity != null) {
         this.createDate = entity.getCreateDate();
         this.createdBy = entity.getCreatedBy();
         this.modifyDate = entity.getModifyDate();
         this.modifiedBy = entity.getModifiedBy();
      }

   }

   public AuditableEntityDto(LocalDateTime createDate, String createdBy, LocalDateTime modifyDate, String modifiedBy) {
      this.createDate = createDate;
      this.createdBy = createdBy;
      this.modifyDate = modifyDate;
      this.modifiedBy = modifiedBy;
   }
}
