package com.jedakah.drunkards.manager.impl;

import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.UserManager;
import com.jedakah.drunkards.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("prod")
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {

  private final UserRepository userRepository;

  @Override
  public User getUser(Long userId) {

    log.debug("Get User by id: {}", userId);
    User foundUser = userRepository.findOne(userId);
    log.debug("User was found: {}", foundUser);

    return foundUser;
  }

  @Override
  public List<User> getAllUsers() {

    log.debug("Get all users");
    List<User> foundUsers = userRepository.findAll();
    log.debug("Users found: {}", foundUsers);

    return foundUsers;
  }

  @Override
  public User createUser(User user) {

    log.debug("Create user: {}", user);
    User result = userRepository.save(user);
    log.debug("User created: {}", user);

    return result;
  }

  @Override
  public User updateUser(User user) {

    log.debug("Update user: {}", user);
    //TODO: update logic
    return null;
  }
}
