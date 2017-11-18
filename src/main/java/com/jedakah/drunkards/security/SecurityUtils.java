package com.jedakah.drunkards.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

  public MyUserPrincipal getCurrentUserFromSession() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (MyUserPrincipal) authentication.getPrincipal();
  }

  public String getUserNameFromSession() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}
