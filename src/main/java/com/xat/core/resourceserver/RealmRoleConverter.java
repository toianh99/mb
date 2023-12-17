package com.xat.core.resourceserver;

import com.xat.core.security.dto.RoleDto;
import com.xat.core.security.dto.UserDto;
import com.xat.core.security.service.RoleService;
import com.xat.core.security.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.stereotype.Component;

@Component
public class RealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
   public static HashMap<String, UserDto> hashMapUsers = new HashMap();
   UserService userService;
   RoleService roleService;
   private final MappedJwtClaimSetConverter delegate = MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

   public RealmRoleConverter() {
   }

   public RealmRoleConverter(UserService userService) {
      this.userService = userService;
   }

   public Collection<GrantedAuthority> convert(Jwt jwt) {
      Map<String, Object> convertedClaims = this.delegate.convert(jwt.getClaims());
      Collection<GrantedAuthority> result = new ArrayList();
      if (convertedClaims != null) {
         String userName = (String)convertedClaims.get("preferred_username");
         UserDto user = (UserDto)hashMapUsers.get(userName);
         if (user == null) {
            user = this.userService.findByUsername(userName);
         }

         if (user != null) {
            if (user.getRoles() != null && user.getRoles().size() > 0) {
               Iterator var6 = user.getRoles().iterator();

               while(var6.hasNext()) {
                  RoleDto role = (RoleDto)var6.next();
                  SimpleGrantedAuthority simple = new SimpleGrantedAuthority(role.getName());
                  result.add(simple);
               }
            }
         } else {
            user = new UserDto();
            user.setUsername(userName);
            user.setActive(true);
            this.userService.save(user);
         }
      }

      return result;
   }
}
