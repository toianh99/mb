package com.xat.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_location"
)
public class Location extends BaseObject {
   private static final long serialVersionUID = -2438983691987203322L;
   @Column(
      name = "name"
   )
   private String name;
   @Column(
      name = "code"
   )
   private String code;
   @Column(
      name = "longitude"
   )
   private double longitude;
   @Column(
      name = "latitude"
   )
   private double latitude;

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

   public String toString() {
      String str = "{id:" + this.getId() + ", name:" + this.name + ",code:" + this.code + "}";
      return str;
   }
}
