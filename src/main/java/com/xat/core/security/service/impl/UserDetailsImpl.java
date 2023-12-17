package com.xat.core.security.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xat.core.security.domain.User;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String username;
   private String email;
   @JsonIgnore
   private String password;
   private Collection<? extends GrantedAuthority> authorities;

   public UserDetailsImpl(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.password = password;
      this.authorities = authorities;
   }

   public static UserDetailsImpl build(User user) {
      List<GrantedAuthority> authorities = (List)user.getRoles().stream().map((role) -> {
         return new SimpleGrantedAuthority(role.getName());
      }).collect(Collectors.toList());
      return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
   }

   public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.authorities;
   }

   public Long getId() {
      return this.id;
   }

   public String getEmail() {
      return this.email;
   }

   public String getPassword() {
      return this.password;
   }

   public String getUsername() {
      return this.username;
   }

   public boolean isAccountNonExpired() {
      return true;
   }

   public boolean isAccountNonLocked() {
      return true;
   }

   public boolean isCredentialsNonExpired() {
      return true;
   }

   public boolean isEnabled() {
      return true;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         UserDetailsImpl user = (UserDetailsImpl)o;
         return Objects.equals(this.id, user.id);
      } else {
         return false;
      }
   }
}
