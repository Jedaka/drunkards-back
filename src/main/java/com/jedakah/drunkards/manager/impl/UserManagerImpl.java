package com.jedakah.drunkards.manager.impl;

import com.jedakah.drunkards.converters.UserConverter;
import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.UserManager;
import com.jedakah.drunkards.repository.UserRepository;
import com.jedakah.drunkards.security.SecurityUtils;
import com.jedakah.drunkards.to.user.CreateUserRequest;
import com.jedakah.drunkards.to.user.GetUserResponse;
import java.util.List;
import java.util.stream.Collectors;
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
  private final UserConverter userConverter;

  @Override
  public GetUserResponse getUser(Long userId) {

    log.debug("Get User by id: {}", userId);

    if (userId == 0) {
      return getCurrentUser();
    }

    User foundUser = userRepository.findOne(userId);
    GetUserResponse userResponse = userConverter.convertUser(foundUser);
    log.debug("User was found: {}", foundUser);

    return userResponse;
  }

  @Override
  public List<GetUserResponse> getAllUsers() {

    log.debug("Get all users");
    List<User> foundUsers = userRepository.findAll();
    List<GetUserResponse> userResponseList = foundUsers.stream()
        .map(userConverter::convertUser)
        .collect(Collectors.toList());
    log.debug("Users found: {}", userResponseList);

    return userResponseList;
  }

  @Override
  public GetUserResponse createUser(CreateUserRequest request) {

    log.debug("Create request: {}", request);
    User user = userConverter.convertRequest(request);
    User savedUser = userRepository.save(user);
    GetUserResponse getUserResponse = userConverter.convertUser(savedUser);
    log.debug("User created: {}", getUserResponse);

    return getUserResponse;
  }

  @Override
  public GetUserResponse updateUser(CreateUserRequest user) {

    log.debug("Update user: {}", user);
    //TODO: update logic
    return null;
  }

  @Override
  public GetUserResponse getCurrentUser() {

    String userName = SecurityUtils.getUserNameFromSession();
    User currentUser = userRepository.findByName(userName);
    GetUserResponse getCurrentUserResponse = userConverter.convertUser(currentUser);
    log.debug("Current user: {}", getCurrentUserResponse);
    return getCurrentUserResponse;
  }
}
