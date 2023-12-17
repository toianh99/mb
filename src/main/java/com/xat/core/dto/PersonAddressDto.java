package com.xat.core.dto;

import com.xat.core.domain.PersonAddress;
import java.util.UUID;

public class PersonAddressDto {
   private UUID id;
   private String address;
   private String city;
   private String province;
   private String country;
   private String postalCode;
   private String latitude;
   private String longitude;
   private UUID personId;
   private Integer type;

   public PersonAddress toEntity() {
      PersonAddress entity = new PersonAddress();
      entity.setId(this.id);
      entity.setAddress(this.address);
      entity.setCity(this.city);
      entity.setProvince(this.province);
      entity.setCountry(this.country);
      entity.setPostalCode(this.postalCode);
      entity.setLatitude(this.latitude);
      entity.setLongitude(this.longitude);
      entity.setType(this.type);
      return entity;
   }

   public PersonAddressDto() {
   }

   public PersonAddressDto(PersonAddress p) {
      if (p != null) {
         this.id = p.getId();
         this.address = p.getAddress();
         this.city = p.getCity();
         this.country = p.getCountry();
         this.latitude = p.getLatitude();
         this.longitude = p.getLongitude();
         this.postalCode = p.getPostalCode();
         this.province = p.getProvince();
         this.type = p.getType();
         if (p.getPerson() != null) {
            this.personId = p.getPerson().getId();
         }

      }
   }

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getProvince() {
      return this.province;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public String getCountry() {
      return this.country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
   }

   public String getLatitude() {
      return this.latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }

   public String getLongitude() {
      return this.longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public UUID getPersonId() {
      return this.personId;
   }

   public void setPersonId(UUID personId) {
      this.personId = personId;
   }

   public Integer getType() {
      return this.type;
   }

   public void setType(Integer type) {
      this.type = type;
   }
}
