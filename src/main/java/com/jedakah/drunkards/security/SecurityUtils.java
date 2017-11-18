package com.jedakah.drunkards.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

public class SecurityUtils {

  public static MyUserPrincipal getCurrentUserFromSession() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (MyUserPrincipal) authentication.getPrincipal();
  }

  public static String getUserNameFromSession() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    //maximum dirty hack
    if (StringUtils.isEmpty(authentication.getName())) {
      return "host";
    }

    return authentication.getName();
  }
}
