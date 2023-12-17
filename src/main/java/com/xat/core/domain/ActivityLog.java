package com.xat.core.domain;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(
   name = "tbl_activity_log"
)
public class ActivityLog extends BaseObject {
   private static final long serialVersionUID = 1L;
   @Column(
      name = "module_log"
   )
   private String moduleLog;
   @Column(
      name = "content_log",
      nullable = false,
      length = 10000
   )
   private String contentLog;
   @Column(
      name = "log_type"
   )
   private Integer logType;
   @Column(
      name = "log_date",
      nullable = true
   )
   private LocalDateTime logDate;
   @Column(
      name = "user_name",
      nullable = true
   )
   private String userName;
   @Column(
      name = "entity_object_type",
      nullable = true
   )
   private String entityObjectType;

   public String getContentLog() {
      return this.contentLog;
   }

   public void setContentLog(String contentLog) {
      this.contentLog = contentLog;
   }

   public Integer getLogType() {
      return this.logType;
   }

   public void setLogType(Integer logType) {
      this.logType = logType;
   }

   public LocalDateTime getLogDate() {
      return this.logDate;
   }

   public void setLogDate(LocalDateTime logDate) {
      this.logDate = logDate;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getModuleLog() {
      return this.moduleLog;
   }

   public void setModuleLog(String moduleLog) {
      this.moduleLog = moduleLog;
   }

   public String getEntityObjectType() {
      return this.entityObjectType;
   }

   public void setEntityObjectType(String entityObjectType) {
      this.entityObjectType = entityObjectType;
   }

   public ActivityLog() {
      this.setId(UUID.randomUUID());
   }
}
