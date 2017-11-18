//package com.jedakah.drunkards.manager.stub;
//
//import com.jedakah.drunkards.entity.User;
//import com.jedakah.drunkards.manager.UserManager;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.List;
//
//@Profile(value = "stub")
//@Component
//public class UserManagerStubImpl implements UserManager{
//
//    @Override
//    public User getUser(Long userId) {
//
//        User user = new User();
//        user.setTelephoneNumber("8-800-555-35-35");
//        user.setAge(40);
//
//        return user;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//
//        User user = new User();
//        user.setTelephoneNumber("8-800-555-35-35");
//        user.setAge(40);
//
//        return Collections.singletonList(user);
//    }
//
//    @Override
//    public User createUser(User user) {
//        return user;
//    }
//
//    @Override
//    public User updateUser(User user) {
//        return user;
//    }
//}
