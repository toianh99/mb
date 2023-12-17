package com.globits.security.service;

import com.globits.security.dto.UserGroupDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserGroupService {
   UserGroupDto findById(Long id);

   Page<UserGroupDto> findList(int pageIndex, int pageSize);

   List<UserGroupDto> findAll();

   UserGroupDto save(UserGroupDto dto);

   void delete(UserGroupDto[] dtos);
}
