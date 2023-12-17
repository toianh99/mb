package com.xat.core.security.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class OauthAccessToken {
   @Column(
      name = "token_id"
   )
   String tokenId;
   @Column(
      name = "token",
      nullable = true,
      length = 16777125
   )
   byte[] token;
   @Id
   @Column(
      name = "authentication_id",
      unique = true,
      nullable = false,
      length = 255
   )
   String authenticationId;
   @Column(
      name = "user_name"
   )
   String userName;
   @Column(
      name = "client_id"
   )
   String clientId;
   @Column(
      name = "authentication",
      nullable = true,
      length = 16777125
   )
   byte[] authentication;
   @Column(
      name = "refresh_token"
   )
   String refreshToken;
   @Column(
      name = "defaultOAuth2AccessToken"
   )
   byte[] defaultOAuth2AccessToken;

   public String getTokenId() {
      return this.tokenId;
   }

   public void setTokenId(String tokenId) {
      this.tokenId = tokenId;
   }

   public byte[] getToken() {
      return this.token;
   }

   public void setToken(byte[] token) {
      this.token = token;
   }

   public String getAuthenticationId() {
      return this.authenticationId;
   }

   public void setAuthenticationId(String authenticationId) {
      this.authenticationId = authenticationId;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getClientId() {
      return this.clientId;
   }

   public void setClientId(String clientId) {
      this.clientId = clientId;
   }

   public byte[] getAuthentication() {
      return this.authentication;
   }

   public void setAuthentication(byte[] authentication) {
      this.authentication = authentication;
   }

   public String getRefreshToken() {
      return this.refreshToken;
   }

   public void setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
   }

   public byte[] getDefaultOAuth2AccessToken() {
      return this.defaultOAuth2AccessToken;
   }

   public void setDefaultOAuth2AccessToken(byte[] defaultOAuth2AccessToken) {
      this.defaultOAuth2AccessToken = defaultOAuth2AccessToken;
   }
}
