package com.xat.core.security.service;

import com.xat.core.security.dto.UserGroupDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserGroupService {
   UserGroupDto findById(Long id);

   Page<UserGroupDto> findList(int pageIndex, int pageSize);

   List<UserGroupDto> findAll();

   UserGroupDto save(UserGroupDto dto);

   void delete(UserGroupDto[] dtos);
}
