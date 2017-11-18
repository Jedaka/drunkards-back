package com.jedakah.drunkards.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.UserManager;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createUser = userManager.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {

        User user = userManager.getUser(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllUsers() {

        List<User> users = userManager.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
