package com.xat.core.service;

import com.xat.core.domain.ActivityLog;
import com.xat.core.domain.BaseObject;
import com.xat.core.dto.ActivityLogDto;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ActivityLogService extends GenericService<ActivityLog, UUID> {
   void saveActivityLog(ActivityLogDto dto);

   void createActivityLogByEntity(BaseObject entity, int actionType);

   Page<ActivityLogDto> getListByPage(Integer pageSize, Integer pageIndex);

   ActivityLogDto getActivityLog(UUID id);

   void createActivityLogByObject(Class<? extends Serializable> entity, int actionType);
}
