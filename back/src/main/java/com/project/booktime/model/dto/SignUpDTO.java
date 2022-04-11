package com.project.booktime.model.dto;

public class SignUpDTO {

    private String email;
    private String password;

    public SignUpDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public SignUpDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignUpDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
