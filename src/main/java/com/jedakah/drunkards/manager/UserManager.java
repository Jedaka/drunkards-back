package com.jedakah.drunkards.manager;

import com.jedakah.drunkards.to.user.CreateUserRequest;
import com.jedakah.drunkards.to.user.GetUserResponse;
import java.util.List;

public interface UserManager {

    GetUserResponse getUser(Long userId);
    List<GetUserResponse> getAllUsers();
    GetUserResponse createUser(CreateUserRequest user);
    GetUserResponse updateUser(CreateUserRequest user);
    GetUserResponse getCurrentUser();
}
