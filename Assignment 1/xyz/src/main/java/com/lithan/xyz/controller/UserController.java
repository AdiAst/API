package com.lithan.xyz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lithan.xyz.entities.User;
import com.lithan.xyz.service.WebService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private WebService webService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = webService.createUser(user);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return webService.loginUser(user.getUsername(), user.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<User> logoutUser(@RequestBody User user) {
        webService.logoutUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
