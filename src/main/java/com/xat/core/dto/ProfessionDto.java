package com.xat.core.dto;

import com.globits.core.domain.Profession;
import java.util.UUID;

public class ProfessionDto {
   private UUID id;
   private String name;
   private String code;
   private String description;
   private boolean isDuplicate;
   private String dupName;
   private String dupCode;

   public ProfessionDto() {
   }

   public ProfessionDto(Profession entity) {
      if (entity != null) {
         this.id = entity.getId();
         this.code = entity.getCode();
         this.name = entity.getName();
         this.description = entity.getDescription();
      }
   }

   public Profession toEntity() {
      Profession entity = new Profession();
      entity.setId(this.id);
      entity.setCode(this.code);
      entity.setName(this.name);
      entity.setDescription(this.description);
      return entity;
   }

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

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

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isDuplicate() {
      return this.isDuplicate;
   }

   public void setDuplicate(boolean isDuplicate) {
      this.isDuplicate = isDuplicate;
   }

   public String getDupName() {
      return this.dupName;
   }

   public void setDupName(String dupName) {
      this.dupName = dupName;
   }

   public String getDupCode() {
      return this.dupCode;
   }

   public void setDupCode(String dupCode) {
      this.dupCode = dupCode;
   }
}
