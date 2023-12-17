package com.xat.core.security.domain;

import jakarta.persistence.Column;

public class OauthRefreshToken {
   @Column(
      name = "token_id"
   )
   private String tokenId;
   @Column(
      name = "token",
      nullable = true,
      length = 16777125
   )
   private byte[] token;
}
