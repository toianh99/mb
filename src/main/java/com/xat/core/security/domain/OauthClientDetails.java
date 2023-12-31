package com.xat.core.security.domain;

import jakarta.persistence.Column;

public class OauthClientDetails {
   @Column(
      name = "client_id"
   )
   private String clientId;
   @Column(
      name = "resource_ids"
   )
   private String resourceIds;
   @Column(
      name = "client_secret"
   )
   private String clientSecret;
   private String scope;
   @Column(
      name = "authorized_grant_types"
   )
   private String authorizedGrantTypes;
   @Column(
      name = "web_server_redirect_uri"
   )
   private String webServerRedirectUri;
   @Column(
      name = "authorities"
   )
   private String authorities;
   @Column(
      name = "access_token_validity"
   )
   private int accessTokenValidity;
   @Column(
      name = "refresh_token_validity"
   )
   private int refreshTokenValidity;
   @Column(
      name = "additional_information"
   )
   private String additionalInformation;
   @Column(
      name = "autoapprove"
   )
   private String autoApprove;

   public String getClientId() {
      return this.clientId;
   }

   public void setClientId(String clientId) {
      this.clientId = clientId;
   }

   public String getResourceIds() {
      return this.resourceIds;
   }

   public void setResourceIds(String resourceIds) {
      this.resourceIds = resourceIds;
   }

   public String getClientSecret() {
      return this.clientSecret;
   }

   public void setClientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
   }

   public String getScope() {
      return this.scope;
   }

   public void setScope(String scope) {
      this.scope = scope;
   }

   public String getAuthorizedGrantTypes() {
      return this.authorizedGrantTypes;
   }

   public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
      this.authorizedGrantTypes = authorizedGrantTypes;
   }

   public String getWebServerRedirectUri() {
      return this.webServerRedirectUri;
   }

   public void setWebServerRedirectUri(String webServerRedirectUri) {
      this.webServerRedirectUri = webServerRedirectUri;
   }

   public String getAuthorities() {
      return this.authorities;
   }

   public void setAuthorities(String authorities) {
      this.authorities = authorities;
   }

   public int getAccessTokenValidity() {
      return this.accessTokenValidity;
   }

   public void setAccessTokenValidity(int accessTokenValidity) {
      this.accessTokenValidity = accessTokenValidity;
   }

   public int getRefreshTokenValidity() {
      return this.refreshTokenValidity;
   }

   public void setRefreshTokenValidity(int refreshTokenValidity) {
      this.refreshTokenValidity = refreshTokenValidity;
   }

   public String getAdditionalInformation() {
      return this.additionalInformation;
   }

   public void setAdditionalInformation(String additionalInformation) {
      this.additionalInformation = additionalInformation;
   }

   public String getAutoApprove() {
      return this.autoApprove;
   }

   public void setAutoApprove(String autoApprove) {
      this.autoApprove = autoApprove;
   }
}
