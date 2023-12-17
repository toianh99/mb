package com.xat.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_room"
)
public class Room extends BaseObject {
   private static final long serialVersionUID = -2208752009903206352L;
   @Column(
      name = "name"
   )
   private String name;
   @Column(
      name = "code"
   )
   private String code;
   @Column(
      name = "capacity"
   )
   private Integer capacity;
   @Column(
      name = "exam_capacity"
   )
   private Integer examCapacity;
   @ManyToOne(
      fetch = FetchType.EAGER
   )
   @JoinColumn(
      name = "building_id"
   )
   private Building building;

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

   public Integer getCapacity() {
      return this.capacity;
   }

   public void setCapacity(Integer capacity) {
      this.capacity = capacity;
   }

   public Building getBuilding() {
      return this.building;
   }

   public void setBuilding(Building building) {
      this.building = building;
   }

   public Integer getExamCapacity() {
      return this.examCapacity;
   }

   public void setExamCapacity(Integer examCapacity) {
      this.examCapacity = examCapacity;
   }

   public Room() {
   }

   public Room(Room room) {
      super(room);
      this.name = room.getName();
      this.code = room.getCode();
      this.capacity = room.getCapacity();
      this.building = room.getBuilding();
   }
}
