package com.project.booktime.model.dto;

import com.project.booktime.model.entity.Picture;

import java.util.Date;

public class UserDTO {

    private String id;
    private String pseudo;
    private String name;
    private String email;
    private Date birthday;
    private Picture picture;

    public UserDTO(String id, String pseudo, String name, String email, Date birthday, Picture picture) {
        this.id = id;
        this.pseudo = pseudo;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.picture = picture;
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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
