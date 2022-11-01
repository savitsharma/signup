package com.signup.controller;

import com.signup.dto.LoginDto;
import com.signup.dto.UserDto;
import com.signup.service.serviceimpl.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    // handler method to handle user registration request


    // handler method to handle register user form submit request
    @PostMapping("/register")
    public ResponseEntity<UserDto> saveAllowanc(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/register/{emailId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("emailId") String emailId) {
        UserDto alluser = this.userService.signupByEmail(emailId);
        ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(alluser));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        String msg = this.userService.login(loginDto);

        return msg;
                //ResponseEntity(HttpStatus.FOUND, HttpStatus.valueOf(msg));
        // return new ResponseEntity<>(loginDto1, HttpStatus.ACCEPTED);


    }
}

