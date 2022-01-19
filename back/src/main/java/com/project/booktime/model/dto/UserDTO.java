package com.project.booktime.model.dto;

import com.project.booktime.model.entity.Picture;

import java.util.Date;

public class UserDTO {

    private String id;
    private String pseudo;
    private String name;
    private String firstName;
    private String email;
    private Date birthday;
    private String base64;

    public UserDTO(String id, String pseudo, String name, String firstName, String email, Date birthday, String base64) {
        this.id = id;
        this.pseudo = pseudo;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.birthday = birthday;
        this.base64 = base64;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
