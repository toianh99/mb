package com.xat.core.dto;

import com.globits.core.domain.Ethnics;
import java.util.UUID;

public class EthnicsDto {
   private UUID id;
   private String name;
   private String code;
   private String description;
   private boolean isDuplicate;
   private String dupName;
   private String dupCode;

   public EthnicsDto() {
   }

   public EthnicsDto(Ethnics eth) {
      this.id = eth.getId();
      this.code = eth.getCode();
      this.name = eth.getName();
      this.description = eth.getDescription();
   }

   public Ethnics toEntity() {
      Ethnics entity = new Ethnics();
      entity.setId(this.id);
      entity.setName(this.name);
      entity.setCode(this.code);
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
