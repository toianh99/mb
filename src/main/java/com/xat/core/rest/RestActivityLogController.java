package com.xat.core.rest;

import com.globits.core.dto.ActivityLogDto;
import com.globits.core.service.ActivityLogService;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/activitylog"})
public class RestActivityLogController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private ActivityLogService activityLogService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN"})
   public Page<ActivityLogDto> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<ActivityLogDto> page = this.activityLogService.getListByPage(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{activityLogId}"},
      method = {RequestMethod.GET}
   )
   public ActivityLogDto getActivityLog(@PathVariable("activityLogId") String activityLogId) {
      ActivityLogDto activityLog = this.activityLogService.getActivityLog(UUID.fromString(activityLogId));
      return activityLog;
   }
}
