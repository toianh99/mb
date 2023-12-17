package com.xat.core.utils;

import com.xat.core.security.domain.Role;
import com.xat.core.security.domain.User;
import java.util.Iterator;
import java.util.Set;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUtils {
   public static final String Oauth2Jwt = "org.springframework.security.oauth2.jwt.Jwt";

   public static boolean isAuthenticated() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      return CommonUtils.isNotNull(authentication) && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
   }

   public static boolean isUserInRole(User user, String role) {
      if (CommonUtils.isNull(user)) {
         return false;
      } else {
         Set<GrantedAuthority> roles = (Set)user.getAuthorities();
         Iterator var3 = roles.iterator();

         GrantedAuthority ga;
         do {
            if (!var3.hasNext()) {
               return false;
            }

            ga = (GrantedAuthority)var3.next();
         } while(!(ga instanceof Role) || !ga.getAuthority().equals(role));

         return true;
      }
   }

   public static User getCurrentUser() {
      if (!isAuthenticated()) {
         return null;
      } else {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         return (User)authentication.getPrincipal();
      }
   }

   public static String getCurrentUserName() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Object principal = authentication.getPrincipal();
      if ("org.springframework.security.oauth2.jwt.Jwt".equals(principal.getClass().getName())) {
         Jwt jwt = (Jwt)principal;
         return jwt.getClaimAsString("preferred_username");
      } else {
         User user = (User)authentication.getPrincipal();
         return user.getUsername();
      }
   }

   public static Object getPrincipal() {
      if (!isAuthenticated()) {
         return null;
      } else {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         return authentication.getPrincipal();
      }
   }

   public static void setCurrentUser(User user) {
      Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
   }

   public static String getHashPassword(String password) {
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      return passwordEncoder.encode(password);
   }

   public static boolean passwordsMatch(String encryptedPassword, String plainPassword) {
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      return passwordEncoder.matches(plainPassword, encryptedPassword);
   }
}
