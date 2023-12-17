package com.xat.core.repository;

import com.globits.core.domain.ActivityLog;
import com.globits.core.dto.ActivityLogDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
   @Query("select new com.globits.core.dto.ActivityLogDto(b,false) from ActivityLog b")
   Page<ActivityLogDto> getListByPage(Pageable pageable);

   @Query("select new com.globits.core.dto.ActivityLogDto(b) from ActivityLog b where b.id=?1")
   ActivityLogDto getActivityLogById(UUID id);
}
