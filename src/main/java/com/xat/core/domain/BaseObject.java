package com.xat.core.domain;

import com.globits.core.auditing.AuditableEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.Type;

@MappedSuperclass
public class BaseObject extends AuditableEntity {
   private static final long serialVersionUID = 1L;
   @Id
   @Type(
      type = "uuid-char"
   )
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
   @Type(
      type = "uuid-char"
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
