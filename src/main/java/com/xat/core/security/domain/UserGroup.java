package com.xat.core.security.domain;

import com.xat.core.auditing.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_user_group"
)
public class UserGroup extends AuditableEntity {
   private static final long serialVersionUID = 3895539245329823070L;
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      name = "name",
      length = 100,
      nullable = false
   )
   private String name;
   @Column(
      name = "description",
      length = 1000,
      nullable = true
   )
   private String description;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
