package com.jedakah.drunkards.controller;

//import org.apache.commons.lang3.NotImplementedException;
//import org.cafejava.boxroom.domain.SocialUser;
//import org.cafejava.boxroom.security.BoxroomUserAuthenticationToken;
//import org.cafejava.boxroom.security.BoxroomUserDetails;
//import org.cafejava.boxroom.service.AccountService;
//import org.cafejava.boxroom.social.oauth2.SocialInfoConverter;
//import org.cafejava.boxroom.social.oauth2.SocialInfoLocator;
//import org.cafejava.boxroom.social.oauth2.SocialUserInfo;
//import org.cafejava.boxroom.social.oauth2.impl.GoogleSocialInfoConverter;
//import org.cafejava.boxroom.social.oauth2.impl.VKontakteSocialInfoConverter;
import java.util.Collections;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(SocialEndpoint.SOCIAL_ENDPOINT_PATH)
public class SocialEndpoint {

  public static final String SOCIAL_ENDPOINT_PATH = "/auth/social";

  public static final String SIGN_IN_PATH = "/{providerId}/signin";

  public static final String SIGN_UP_PATH = "/{providerId}/signup";

  public static final String CONNECT_PATH = "/{providerId}/connect";
  public static final String CODE_PARAM_NAME = "code";
  public static final String ERROR_PARAM_NAME = "error";

  private static final Logger LOG = LoggerFactory.getLogger(SocialEndpoint.class);
//
//  @Inject
//  private SocialInfoLocator socialInfoLocator;
//
//  @Inject
//  private AccountService accountService;

  @Inject
  private ConnectionFactoryLocator factoryLocator;

  private static Map<String, String> socialEndpoints = new HashMap<>();
  static {
    socialEndpoints.put("vkontakte", "http://localhost:8080");
  }

  @RequestMapping(path = CONNECT_PATH, method = RequestMethod.POST)
  public void processAddRedirectResponse(HttpServletResponse response,
      @PathVariable String providerId) throws IOException {
    socialRedirect(response, CONNECT_PATH, providerId);
  }

  @RequestMapping(path = SocialEndpoint.CONNECT_PATH, method = RequestMethod.GET)
  public void processConnectSocialCallbackResponse(HttpServletRequest request,
      HttpServletResponse response, @PathVariable String providerId,
      @RequestParam(required = false) String code,
      @RequestParam(required = false) String error) throws IOException {

    if (request.getParameterMap().isEmpty()) {
      socialRedirect(response, CONNECT_PATH, providerId);
      return;
    }
    if (code == null) {
      LOG.warn("user has not been authenticated");
      //TODO redirect
      response.sendRedirect("/login?error");
      return;
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
      LOG.warn("User is not authenticated");
      //TODO redirect
      response.sendRedirect("/login?error");
      return;
    }
    String callbackEndpoint = getEndpoint(providerId);
    String callbackURL = buildCallbackURL(callbackEndpoint, CONNECT_PATH, providerId);

    OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) factoryLocator
        .getConnectionFactory(providerId);
    AccessGrant accessGrant = connectionFactory.getOAuthOperations()
        .exchangeForAccess(code, callbackURL, null);

    Connection<?> connection = connectionFactory.createConnection(accessGrant);
    ConnectionKey key = connection.getKey();

    Long accountId = getAccountId(authentication);
//    accountService.addSocial(accountId, providerId, key.getProviderUserId());
    //TODO redirect
  }

//  @RequestMapping(path = SIGN_UP_PATH, method = RequestMethod.POST)
//  public void processSignUpRedirectResponse(HttpServletResponse response,
//      @PathVariable String providerId) throws IOException {
//    socialRedirect(response, SIGN_UP_PATH, providerId);
//  }

  private void socialRedirect(HttpServletResponse response, String callbackPath, String providerId)
      throws IOException {
    LOG.debug("Redirect to social. Provider:{}, callback path:{}", providerId, callbackPath);
    String callbackEndpoint = getEndpoint(providerId);
    String callbackURL = buildCallbackURL(callbackEndpoint, callbackPath, providerId);
    String authUrl = getSocialOAuth2URL(callbackURL,
        (OAuth2ConnectionFactory<?>) factoryLocator.getConnectionFactory(providerId));
    response.sendRedirect(authUrl);
  }

  private String getEndpoint(String providerId) {

    return socialEndpoints.computeIfAbsent(providerId, o -> {
      LOG.warn("Unsupported provider: {}", providerId);
      throw new IllegalArgumentException("Unsupported provider: " + providerId);
    });
  }

//  @RequestMapping(path = SocialEndpoint.SIGN_UP_PATH, method = RequestMethod.GET)
//  public String processSignUpCallBackResponse(HttpServletRequest request,
//      HttpServletResponse response, @PathVariable String providerId,
//      @RequestParam(required = false) String code,
//      @RequestParam(required = false) String error,
//      RedirectAttributes redirectAttributes) throws IOException {
//    if (request.getParameterMap().isEmpty()) {
//      String callbackEndpoint = getEndpoint(providerId);
//      String callbackURL = buildCallbackURL(callbackEndpoint, SocialEndpoint.SIGN_UP_PATH,
//          providerId);
//      String authUrl = getSocialOAuth2URL(callbackURL,
//          (OAuth2ConnectionFactory<?>) factoryLocator.getConnectionFactory(providerId));
//      return "redirect:" + authUrl;
//    }
//
//    if (code == null) {
//      LOG.warn("user has not been authenticated");
//      //response.sendRedirect("/login?error");
//      return "redirect:/login?error";
//    }
//    LOG.debug("Successful auth. Provider:{}, code:{}", providerId, code);
//    String callbackEndpoint = getEndpoint(providerId);
//    String callbackURL = buildCallbackURL(callbackEndpoint, SIGN_UP_PATH, providerId);
//
//    OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) factoryLocator
//        .getConnectionFactory(providerId);
//    AccessGrant accessGrant = connectionFactory.getOAuthOperations()
//        .exchangeForAccess(code, callbackURL, null);
//
//    Connection<?> connection = connectionFactory.createConnection(accessGrant);
//
//    SocialUser socialUser = accountService.findSocialUser(providerId, key.getProviderUserId());
//    if (socialUser != null) {
//      //socialRedirect(response, SIGN_IN_PATH, providerId);
//      //return;
//      return "redirect:/login?error";
//    }
//
//    SocialUserInfo userInfo = socialInfoLocator.getUserInfo(connection);
//
//    redirectAttributes.addFlashAttribute(SocialSignUpController.USER_INFO_ATTR, userInfo);
//    //response.sendRedirect("/signup/social");
//    return "redirect:/signup/social";
//  }

  private Long getAccountId(Authentication authentication) {
    if (authentication instanceof UsernamePasswordAuthenticationToken) {
      User user = (User) authentication.getPrincipal();
//      return getAccountId(user.getUsername());
    }
//    if (authentication instanceof BoxroomUserAuthenticationToken) {
//      BoxroomUserDetails user = (BoxroomUserDetails) authentication.getPrincipal();
//      return user.getAccountId();
//    }
    throw new IllegalArgumentException(
        "Unsupported authentication class: " + authentication.getClass());
  }

//  private Long getAccountId(String username) {
//    return accountService.getAccountId(username);
//  }

//  private Authentication loadSocialUser(String providerId, String userId) {
//    SocialUser socialUser = accountService.findSocialUser(providerId, userId);
//    if (socialUser == null) {
//      return null;
//    }
//    throw new NotImplementedException("Not implemented social login");
//  }

//  @Inject
//  public void setAccountService(AccountService accountService) {
//    this.accountService = accountService;
//  }


  private String buildCallbackURL(String endpoint, String callbackPath, String providerId) {

    Map<String, Object> uriParams = new HashMap<>();
    uriParams.put("providerId", providerId);

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);

    builder.path(SocialEndpoint.SOCIAL_ENDPOINT_PATH).path(callbackPath);
    String url = builder.buildAndExpand(uriParams).toUriString();
    return url;
  }

  private String getSocialOAuth2URL(String callbackURL,
      OAuth2ConnectionFactory<?> connectionFactory) {
    OAuth2Parameters parameters = new OAuth2Parameters();
    parameters.setRedirectUri(callbackURL);
    parameters.setScope(connectionFactory.getScope());
    String authUrl = connectionFactory.getOAuthOperations().buildAuthenticateUrl(parameters);
    return authUrl;
  }
}