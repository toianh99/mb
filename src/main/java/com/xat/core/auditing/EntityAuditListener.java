package com.xat.core.auditing;

import com.xat.core.utils.CommonUtils;
import com.xat.core.utils.SecurityUtils;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.joda.time.LocalDateTime;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

public class EntityAuditListener {
   @PostRemove
   public void postRemove(AuditableEntity auditableEntity) {
      LocalDateTime now = LocalDateTime.now();
      auditableEntity.setModifyDate(now);
      Object principal = SecurityUtils.getPrincipal();
      if (CommonUtils.isNotNull(principal)) {
         if (principal instanceof UserDetails) {
            UserDetails var4 = (UserDetails)principal;
         } else if (principal instanceof Jwt) {
            Jwt jwt = (Jwt)principal;
            if (jwt.getClaims() != null && jwt.getClaims().get("preferred_username") != null) {
               String var5 = jwt.getClaims().get("preferred_username").toString();
            }
         }
      }

   }

   @PrePersist
   public void beforePersit(AuditableEntity auditableEntity) {
      LocalDateTime now = LocalDateTime.now();
      auditableEntity.setCreateDate(now);
      auditableEntity.setModifyDate(now);
      Object principal = SecurityUtils.getPrincipal();
      if (CommonUtils.isNotNull(principal)) {
         if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails)principal;
            auditableEntity.setCreatedBy(user.getUsername());
            auditableEntity.setModifiedBy(user.getUsername());
         } else if (principal instanceof Jwt) {
            Jwt jwt = (Jwt)principal;
            if (jwt.getClaims() != null && jwt.getClaims().get("preferred_username") != null) {
               String userName = jwt.getClaims().get("preferred_username").toString();
               auditableEntity.setCreatedBy(userName);
               auditableEntity.setModifiedBy(userName);
            }
         } else {
            auditableEntity.setCreatedBy(principal.toString());
            auditableEntity.setModifiedBy(principal.toString());
         }
      } else {
         auditableEntity.setCreatedBy("admin");
      }

   }

   @PreUpdate
   public void beforeMerge(AuditableEntity auditableEntity) {
      LocalDateTime now = LocalDateTime.now();
      auditableEntity.setModifyDate(now);
      Object principal = SecurityUtils.getPrincipal();
      if (CommonUtils.isNotNull(principal)) {
         if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails)principal;
            auditableEntity.setCreatedBy(user.getUsername());
            auditableEntity.setModifiedBy(user.getUsername());
         } else if (principal instanceof Jwt) {
            Jwt jwt = (Jwt)principal;
            if (jwt.getClaims() != null && jwt.getClaims().get("preferred_username") != null) {
               String userName = jwt.getClaims().get("preferred_username").toString();
               auditableEntity.setCreatedBy(userName);
               auditableEntity.setModifiedBy(userName);
            }
         } else {
            auditableEntity.setCreatedBy(principal.toString());
            auditableEntity.setModifiedBy(principal.toString());
         }
      }

   }
}
