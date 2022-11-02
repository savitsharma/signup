package com.signup.controller;

import com.signup.dto.LoginDto;
import com.signup.dto.UserDto;

import com.signup.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasAnyRole")
@RequestMapping("/usercontroller")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // handler method to handle user registration request


    // handler method to handle register user form submit request
    @PreAuthorize("hasAnyRole(ADMIN)")
    @PostMapping("/register")
    public ResponseEntity<UserDto> saveAllowanc(@RequestBody UserDto userDto) {
        String pwd = userDto.getPassword();
        String encryptPwd=passwordEncoder.encode(pwd);
        userDto.setPassword(encryptPwd);
        userService.saveUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/register/{emailId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("emailId") String emailId) {
        UserDto alluser = this.userService.signupByEmail(emailId);
        ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(alluser));
    }

    @PostMapping("/login1")
    public String login(@RequestBody LoginDto loginDto) {
        String msg = this.userService.login(loginDto);

        return msg;
                //ResponseEntity(HttpStatus.FOUND, HttpStatus.valueOf(msg));
        // return new ResponseEntity<>(loginDto1, HttpStatus.ACCEPTED);


    }
}

