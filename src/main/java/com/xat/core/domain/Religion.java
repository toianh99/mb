package com.xat.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(
   name = "tbl_religion"
)
@XmlRootElement
public class Religion extends BaseObject {
   private static final long serialVersionUID = 6159909161433854890L;
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
}
