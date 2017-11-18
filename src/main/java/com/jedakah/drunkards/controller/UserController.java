package com.jedakah.drunkards.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jedakah.drunkards.manager.UserManager;
import com.jedakah.drunkards.to.user.CreateUserRequest;
import com.jedakah.drunkards.to.user.GetUserResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "", method = RequestMethod.POST,
        consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request) {

        GetUserResponse userResponse = userManager.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("userId") Long userId) {

        GetUserResponse userResponse = userManager.getUser(userId);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {

        List<GetUserResponse> userResponseList = userManager.getAllUsers();

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }


}
