package com.jedakah.drunkards.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

  public static MyUserPrincipal getCurrentUserFromSession() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (MyUserPrincipal) authentication.getPrincipal();
  }

  public static String getUserNameFromSession() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    //maximum dirty hack
    if (authentication == null) {
      return "host";
    }

    return authentication.getName();
  }
}
