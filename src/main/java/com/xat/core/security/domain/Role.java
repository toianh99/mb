package com.xat.core.security.domain;

import com.xat.core.auditing.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;

@Table(
   name = "tbl_role"
)
@Entity
public class Role extends AuditableEntity implements GrantedAuthority {
   @Transient
   private static final long serialVersionUID = 6318192070978248463L;
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      name = "role_name",
      length = 150,
      nullable = false
   )
   private String name;
   @Column(
      name = "role_description",
      length = 512,
      nullable = true
   )
   @Size(
      max = 512,
      message = "{validation.forms.role.description.size}"
   )
   private String description;

   @Transient
   public String getAuthority() {
      return this.name;
   }

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

   @Transient
   public String toString() {
      return String.format("Role: %s, %s", this.name, this.description);
   }
}
