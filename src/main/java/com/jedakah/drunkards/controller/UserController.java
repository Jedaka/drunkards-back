package com.jedakah.drunkards.controller;

import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity createUser(User user) {

        userManager.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {

        User user = userManager.getUser(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List> getAllUsers() {

        List<User> users = userManager.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
