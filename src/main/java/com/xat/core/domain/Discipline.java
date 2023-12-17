package com.xat.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_discipline"
)
public class Discipline extends BaseObject {
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
      name = "description"
   )
   private String description;
   @Column(
      name = "level"
   )
   private Integer level;

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

   public Integer getLevel() {
      return this.level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   public Discipline() {
   }

   public Discipline(Discipline room) {
      super(room);
      this.name = room.getName();
      this.code = room.getCode();
      this.description = room.getDescription();
      this.level = room.getLevel();
   }
}
