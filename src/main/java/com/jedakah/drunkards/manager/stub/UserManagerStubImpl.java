//package com.jedakah.drunkards.manager.stub;
//
//import com.jedakah.drunkards.manager.UserManager;
//import com.jedakah.drunkards.to.user.CreateUserRequest;
//import com.jedakah.drunkards.to.user.GetUserResponse;
//import java.util.Collections;
//import java.util.List;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Profile(value = "stub")
//@Component
//public class UserManagerStubImpl implements UserManager {
//
//    @Override
//    public GetUserResponse getUser(Long userId) {
//
//        GetUserResponse getUserResponse = new GetUserResponse();
//        getUserResponse.setUserId(1l);
//        getUserResponse.setAge(100);
//        getUserResponse.setName("Vasya Pupkin");
//        getUserResponse.setTelephoneNumber("8-800-555-35-35");
//        return getUserResponse;
//    }
//
//    @Override
//    public GetUserResponse getCurrentUser() {
//        return getUser(0L);
//    }
//
//    @Override
//    public List<GetUserResponse> getAllUsers() {
//
//        GetUserResponse getUserResponse = new GetUserResponse();
//        getUserResponse.setUserId(1l);
//        getUserResponse.setAge(100);
//        getUserResponse.setName("Vasya Pupkin");
//        getUserResponse.setTelephoneNumber("8-800-555-35-35");
//        return Collections.singletonList(getUserResponse);
//    }
//
//    @Override
//    public GetUserResponse createUser(CreateUserRequest user) {
//
//        GetUserResponse getUserResponse = new GetUserResponse();
//        getUserResponse.setUserId(1l);
//        getUserResponse.setAge(user.getAge());
//        getUserResponse.setName(user.getName());
//        getUserResponse.setTelephoneNumber(user.getTelephoneNumber());
//
//        return getUserResponse;
//    }
//
//    @Override
//    public GetUserResponse updateUser(CreateUserRequest user) {
//
//        return  createUser(user);
//    }
//}
