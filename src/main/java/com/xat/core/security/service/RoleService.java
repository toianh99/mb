package com.globits.security.service;

import com.globits.core.service.GenericService;
import com.globits.security.domain.Role;
import com.globits.security.dto.RoleDto;
import java.util.List;

public interface RoleService extends GenericService<Role, Long> {
   Role findByName(String name);

   Role findById(Long roleId);

   RoleDto createRole(RoleDto roleDto);

   RoleDto updateRole(RoleDto roleDto, Long roleId);

   List<RoleDto> findAll();
}
