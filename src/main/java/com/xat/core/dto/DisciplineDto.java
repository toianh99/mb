package com.xat.core.dto;

import com.globits.core.domain.Discipline;
import java.util.UUID;

public class DisciplineDto {
   private UUID id;
   private String name;
   private String code;
   private String description;
   private Integer level;
   private boolean isDuplicate;
   private String dupName;
   private String dupCode;

   public DisciplineDto() {
   }

   public DisciplineDto(Discipline entity) {
      if (entity != null) {
         this.id = entity.getId();
         this.code = entity.getCode();
         this.name = entity.getName();
         this.description = entity.getDescription();
         this.level = entity.getLevel();
      }
   }

   public Discipline toEntity() {
      Discipline entity = new Discipline();
      entity.setId(this.id);
      entity.setCode(this.code);
      entity.setName(this.name);
      entity.setDescription(this.description);
      entity.setLevel(this.level);
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

   public void setName(String name) {
      this.name = name;
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

   public Integer getLevel() {
      return this.level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   public String getDupCode() {
      return this.dupCode;
   }

   public void setDupCode(String dupCode) {
      this.dupCode = dupCode;
   }
}
