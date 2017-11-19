package com.jedakah.drunkards.configuration;

import org.springframework.social.oauth2.AccessGrant;

import java.io.Serializable;

/**
 * Created by Pavel.
 * Date: 04.04.2016 23:26.
 */
public class VKAccessGrant extends AccessGrant implements Serializable {

  private final String userId;
  private final String email;

  public VKAccessGrant(String userId, String email, String accessToken) {
    super(accessToken);
    this.userId = userId;
    this.email = email;

  }

  public VKAccessGrant(String userId, String email, String accessToken, String scope, String refreshToken, Long expiresIn) {
    super(accessToken, scope, refreshToken, expiresIn);
    this.userId = userId;
    this.email = email;
  }

  public String getUserId() {
    return userId;
  }


  public String getEmail() {
    return email;
  }


}
