package com.xat.core.dto;

import java.util.UUID;

import com.xat.core.domain.ActivityLog;
import org.joda.time.LocalDateTime;

public class ActivityLogDto extends BaseObjectDto {
   private String contentLog;
   private Integer logType;
   private String userName;
   private LocalDateTime logDate;
   private String moduleLog;
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

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public LocalDateTime getLogDate() {
      return this.logDate;
   }

   public void setLogDate(LocalDateTime logDate) {
      this.logDate = logDate;
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

   public ActivityLogDto() {
      this.setId(UUID.randomUUID());
   }

   public ActivityLogDto(ActivityLog entity) {
      this.contentLog = entity.getContentLog();
      this.userName = entity.getUserName();
      this.setId(entity.getId());
      this.logType = entity.getLogType();
      this.moduleLog = entity.getModuleLog();
      this.logDate = entity.getLogDate();
      this.entityObjectType = entity.getEntityObjectType();
   }

   public ActivityLogDto(ActivityLog entity, Boolean isFull) {
      this.createDate = entity.getCreateDate();
      this.createdBy = entity.getCreatedBy();
      this.userName = entity.getUserName();
      this.setId(entity.getId());
      this.logType = entity.getLogType();
      this.moduleLog = entity.getModuleLog();
      this.logDate = entity.getLogDate();
      this.entityObjectType = entity.getEntityObjectType();
      if (isFull) {
         this.contentLog = entity.getContentLog();
      }

   }

   public ActivityLog toEntity() {
      ActivityLog entity = new ActivityLog();
      entity.setContentLog(this.contentLog);
      entity.setId(this.getId());
      entity.setLogDate(this.logDate);
      entity.setLogType(this.logType);
      entity.setUserName(this.userName);
      entity.setEntityObjectType(this.entityObjectType);
      entity.setModuleLog(this.moduleLog);
      return entity;
   }
}
