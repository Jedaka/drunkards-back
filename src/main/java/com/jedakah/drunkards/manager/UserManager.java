package com.jedakah.drunkards.manager;

import com.jedakah.drunkards.entity.User;

import java.util.List;

public interface UserManager {

    User getUser(Long userId);
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(User user);
}
