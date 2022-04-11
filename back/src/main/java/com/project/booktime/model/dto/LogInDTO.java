package com.project.booktime.model.dto;

public class LogInDTO {

    private String email;
    private String password;

    public LogInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public LogInDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LogInDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
