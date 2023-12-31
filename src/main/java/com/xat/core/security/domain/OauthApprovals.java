package com.xat.core.security.domain;

import java.util.Date;
import jakarta.persistence.Column;

public class OauthApprovals {
   @Column(
      name = "userId"
   )
   private String userId;
   @Column(
      name = "clientId"
   )
   private String clientId;
   @Column(
      name = "scope"
   )
   private String scope;
   @Column(
      name = "status"
   )
   private String status;
   @Column(
      name = "expiresAt"
   )
   private Date expiresAt;
   @Column(
      name = "lastModifiedAt"
   )
   private Date lastModifiedAt;

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getClientId() {
      return this.clientId;
   }

   public void setClientId(String clientId) {
      this.clientId = clientId;
   }

   public String getScope() {
      return this.scope;
   }

   public void setScope(String scope) {
      this.scope = scope;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Date getExpiresAt() {
      return this.expiresAt;
   }

   public void setExpiresAt(Date expiresAt) {
      this.expiresAt = expiresAt;
   }

   public Date getLastModifiedAt() {
      return this.lastModifiedAt;
   }

   public void setLastModifiedAt(Date lastModifiedAt) {
      this.lastModifiedAt = lastModifiedAt;
   }
}
