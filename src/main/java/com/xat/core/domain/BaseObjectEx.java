package com.xat.core.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseObjectEx extends BaseObject {
   private static final long serialVersionUID = 1L;
   @ManyToOne
   @JoinColumn(
      name = "org_id"
   )
   private Organization org;

   public Organization getOrg() {
      return this.org;
   }

   public void setOrg(Organization org) {
      this.org = org;
   }
}
