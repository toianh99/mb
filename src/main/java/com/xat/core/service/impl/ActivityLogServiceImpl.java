package com.xat.core.service.impl;

import com.globits.core.domain.ActivityLog;
import com.globits.core.domain.BaseObject;
import com.globits.core.dto.ActivityLogDto;
import com.globits.core.repository.ActivityLogRepository;
import com.globits.core.service.ActivityLogService;
import com.globits.core.utils.SerializableUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ActivityLogServiceImpl extends GenericServiceImpl<ActivityLog, UUID> implements ActivityLogService {
   @Autowired
   ActivityLogRepository activityLogRepository;

   public void saveActivityLog(ActivityLogDto dto) {
      ActivityLog entity = dto.toEntity();
      this.activityLogRepository.save(entity);
   }

   public void createActivityLogByObject(Class<? extends Serializable> entity, int actionType) {
      ActivityLog log = new ActivityLog();
      log.setEntityObjectType(entity.getClass().getName());

      try {
         String sObject = SerializableUtil.toString(entity);
         if (sObject != null && sObject.length() < 10000) {
            log.setContentLog(sObject);
         } else {
            log.setContentLog("Object  to long to serialize");
         }
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      log.setLogType(actionType);
      this.activityLogRepository.save(log);
   }

   public void createActivityLogByEntity(BaseObject entity, int actionType) {
      ActivityLog log = new ActivityLog();
      log.setEntityObjectType(entity.getClass().getName());

      try {
         String sObject = SerializableUtil.toString(entity);
         if (sObject != null && sObject.length() < 10000) {
            log.setContentLog(sObject);
         } else {
            log.setContentLog("Object with ID =" + entity.getId() + " to long to serialize");
         }
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      log.setLogType(actionType);
      this.activityLogRepository.save(log);
   }

   public Page<ActivityLogDto> getListByPage(Integer pageIndex, Integer pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.activityLogRepository.getListByPage(pageable);
   }

   public ActivityLogDto getActivityLog(UUID id) {
      return this.activityLogRepository.getActivityLogById(id);
   }
}
