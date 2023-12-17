package com.xat.core.domain;

import java.util.UUID;

import com.xat.core.auditing.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Type;

@MappedSuperclass
public class BaseObject extends AuditableEntity {
   private static final long serialVersionUID = 1L;
   @Id
   @Column(
      name = "id",
      unique = true,
      nullable = false,
      length = 36
   )
   private UUID id;
   @Column(
      name = "uuid_key",
      nullable = true
   )
   private UUID uuidKey;
   @Column(
      name = "voided",
      nullable = true
   )
   private Boolean voided;

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public Boolean getVoided() {
      return this.voided;
   }

   public void setVoided(Boolean voided) {
      this.voided = voided;
   }

   public UUID getUuidKey() {
      return this.uuidKey;
   }

   public void setUuidKey(UUID uuidKey) {
      this.uuidKey = uuidKey;
   }

   public BaseObject() {
      if (this.id == null) {
         this.id = UUID.randomUUID();
      }

      this.uuidKey = UUID.randomUUID();
   }

   public BaseObject(BaseObject object) {
      super(object);
      if (object != null) {
         this.uuidKey = UUID.randomUUID();
         this.id = object.getId();
      }

   }
}
