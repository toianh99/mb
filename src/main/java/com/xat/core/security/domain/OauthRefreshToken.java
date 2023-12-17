package com.globits.security.domain;

import javax.persistence.Column;

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
