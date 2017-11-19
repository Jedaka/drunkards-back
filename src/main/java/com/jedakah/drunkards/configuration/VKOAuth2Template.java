package com.jedakah.drunkards.configuration;


import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Created by Pavel.
 * Date: 04.04.2016 23:30.
 */
@Deprecated
public class VKOAuth2Template extends OAuth2Template {

  public static final String AUTHORIZE_URL = "https://oauth.vk.com/authorize";
  public static final String ACCESS_TOKEN_URL = "https://oauth.vk.com/access_token";

  public VKOAuth2Template(String clientId, String clientSecret) {
    super(clientId, clientSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL);
    SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
    simpleClientHttpRequestFactory.setOutputStreaming(false);
    setRequestFactory(simpleClientHttpRequestFactory);
    setUseParametersForClientAuthentication(true);
  }

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  protected VKAccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
    Map<String, Object> responseMap = getRestTemplate().postForObject(accessTokenUrl, parameters, Map.class);
    return extractAccessGrant(responseMap);
  }

  private VKAccessGrant extractAccessGrant(Map<String, Object> result) {
    String userId = result.get("user_id").toString();
    String email = (String) result.get("email");
    String accessToken = (String) result.get("access_token");
    String scope = (String) result.get("scope");
    String refreshToken = (String) result.get("refresh_token");
    // result.get("expires_in") may be an Integer, so cast it to Number first.
    Number expiresInNumber = (Number) result.get("expires_in");
    Long expiresIn = (expiresInNumber == null) ? null : expiresInNumber.longValue();

    return createAccessGrant(userId, email, accessToken, scope, refreshToken, expiresIn, result);
  }

  protected VKAccessGrant createAccessGrant(String userId, String email, String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
    return new VKAccessGrant(userId, email, accessToken, scope, refreshToken, expiresIn);
  }
}