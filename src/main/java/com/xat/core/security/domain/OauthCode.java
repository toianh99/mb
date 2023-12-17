package com.globits.security.domain;

import javax.persistence.Column;

public class OauthCode {
   private String code;
   @Column(
      name = "authentication",
      nullable = true,
      length = 16777125
   )
   private byte[] authentication;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public byte[] getAuthentication() {
      return this.authentication;
   }

   public void setAuthentication(byte[] authentication) {
      this.authentication = authentication;
   }
}
