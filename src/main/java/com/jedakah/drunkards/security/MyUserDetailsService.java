package com.jedakah.drunkards.security;

import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByName(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new MyUserPrincipal(user);
  }
}