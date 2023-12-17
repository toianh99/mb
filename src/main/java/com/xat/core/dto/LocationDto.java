package com.xat.core.dto;

import com.xat.core.domain.Location;

import java.util.UUID;

public class LocationDto {
   private UUID id;
   private String name;
   private String code;
   private double longitude;
   private double latitude;
   private boolean isDuplicate;
   private String dupName;
   private String dupCode;

   public LocationDto() {
   }

   public LocationDto(Location entity, Boolean isGetAll) {
      if (entity != null) {
         this.id = entity.getId();
         this.code = entity.getCode();
         this.name = entity.getName();
         this.latitude = entity.getLatitude();
         this.longitude = entity.getLongitude();
      }
   }

   public LocationDto(Location entity) {
      this(entity, true);
   }

   public Location toEntity() {
      Location entity = new Location();
      entity.setId(this.id);
      entity.setCode(this.code);
      entity.setName(this.name);
      entity.setLatitude(this.latitude);
      entity.setLongitude(this.longitude);
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

   public double getLongitude() {
      return this.longitude;
   }

   public void setLongitude(double longitude) {
      this.longitude = longitude;
   }

   public double getLatitude() {
      return this.latitude;
   }

   public void setLatitude(double latitude) {
      this.latitude = latitude;
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

   public String getDupCode() {
      return this.dupCode;
   }

   public void setDupCode(String dupCode) {
      this.dupCode = dupCode;
   }
}
