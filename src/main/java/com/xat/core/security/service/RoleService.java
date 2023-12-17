package com.xat.core.security.service;

import com.xat.core.service.GenericService;
import com.xat.core.security.domain.Role;
import com.xat.core.security.dto.RoleDto;
import java.util.List;

public interface RoleService extends GenericService<Role, Long> {
   Role findByName(String name);

   Role findById(Long roleId);

   RoleDto createRole(RoleDto roleDto);

   RoleDto updateRole(RoleDto roleDto, Long roleId);

   List<RoleDto> findAll();
}
