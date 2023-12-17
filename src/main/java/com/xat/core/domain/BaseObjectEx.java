package com.xat.core.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

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
