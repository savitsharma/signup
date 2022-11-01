package com.signup.dto;


import org.springframework.context.annotation.Bean;


public class LoginDto {

    private String emailId;
    private String password;


    public String getEmailId() {
        return emailId;
    }

    public void setEmail(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
