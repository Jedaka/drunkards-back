package com.jedakah.drunkards.manager.stub;

import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.UserManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile(value = "stub")
@Component
public class UserManagerStubImpl implements UserManager{

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
