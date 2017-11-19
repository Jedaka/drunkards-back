package com.jedakah.drunkards.converters;

import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.to.user.CreateUserRequest;
import com.jedakah.drunkards.to.user.GetUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  public User convertRequest(CreateUserRequest createUserRequest) {

    User user = new User();
    user.setName(createUserRequest.getName());
    user.setPassword(createUserRequest.getPassword());
    user.setAge(createUserRequest.getAge());
    user.setTelephoneNumber(createUserRequest.getTelephoneNumber());
    user.setFirstName(createUserRequest.getFirstName());
    user.setLastName(createUserRequest.getLastName());

    return user;

  }

  public GetUserResponse convertUser(User user) {

    GetUserResponse getUserResponse = new GetUserResponse();
    getUserResponse.setUserId(user.getId());
    getUserResponse.setName(user.getName());
    getUserResponse.setAge(user.getAge());
    getUserResponse.setTelephoneNumber(user.getTelephoneNumber());
    getUserResponse.setFirstName(user.getFirstName());
    getUserResponse.setLastName(user.getLastName());

    return getUserResponse;
  }

}
