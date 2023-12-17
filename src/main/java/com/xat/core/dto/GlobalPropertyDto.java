package com.xat.core.dto;

import com.xat.core.domain.GlobalProperty;

public class GlobalPropertyDto {
   private String property;
   private String propertyName;
   private String propertyValue;
   private String description;

   public GlobalPropertyDto() {
   }

   public GlobalPropertyDto(GlobalProperty entity, Boolean isGetAll) {
      if (entity != null) {
         this.description = entity.getDescription();
         this.property = entity.getProperty();
         this.propertyName = entity.getPropertyName();
         this.propertyValue = entity.getPropertyValue();
      }
   }

   public GlobalPropertyDto(GlobalProperty entity) {
      this(entity, true);
   }

   public GlobalProperty toEntity() {
      GlobalProperty entity = new GlobalProperty();
      entity.setProperty(this.property);
      entity.setPropertyName(this.propertyName);
      entity.setPropertyValue(this.propertyValue);
      entity.setDescription(this.description);
      return entity;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getProperty() {
      return this.property;
   }

   public void setProperty(String property) {
      this.property = property;
   }

   public String getPropertyName() {
      return this.propertyName;
   }

   public void setPropertyName(String propertyName) {
      this.propertyName = propertyName;
   }

   public String getPropertyValue() {
      return this.propertyValue;
   }

   public void setPropertyValue(String propertyValue) {
      this.propertyValue = propertyValue;
   }
}
