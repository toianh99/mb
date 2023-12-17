package com.globits.security.service;

import com.globits.core.service.GenericService;
import com.globits.security.domain.User;
import com.globits.security.dto.UserDto;
import com.globits.security.dto.UserFilterDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface UserService extends GenericService<User, Long> {
   User findEntityByUsername(String username);

   UserDto findByUsername(String username);

   UserDto findByEmail(String email);

   Page<UserDto> searchByPageBasicInfo(int pageIndex, int pageSize, String username);

   Page<UserDto> findByPageBasicInfo(int pageIndex, int pageSize);

   Page<UserDto> findByPageBasicInfoByOrg(UUID orgId, int pageIndex, int pageSize);

   UserDto save(UserDto user);

   Page<UserDto> findByPage(int pageIndex, int pageSize);

   UserDto getCurrentUser();

   byte[] getProfilePhoto(String username);

   UserDto savePhoto(UserDto dto);

   boolean passwordMatch(UserDto dto);

   UserDto changePassword(UserDto dto);

   Page<UserDto> findByPageUsername(String username, int pageIndex, int pageSize);

   Page<UserDto> findAllPageable(UserFilterDto filter, int pageIndex, int pageSize);

   boolean emailAlreadyUsed(UserDto dto);

   UserDto findByUserId(Long userId);

   UserDto deleteById(Long userId);

   User updateUserLastLogin(Long userId);

   User saveUser(UserDto userDto);

   User getCurrentEntityUser();

   boolean isUserInOrg(UUID orgId);

   boolean isUserInOrg(User user, UUID orgId);

   void recordLoginFailure(Authentication authentication);

   UserDto getKeyCloakCurrentUser();
}
