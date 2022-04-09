package com.project.booktime.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("user")
public class User {

    @Id
    private String id;
    private String pseudo;
    private String name;
    private String firstName;
    private String password;
    private String email;
    private Date birthday;
    private String base64;
    private List<String> added;
    private List<String> liked;

    public User(String pseudo, String name, String firstName, String password, String email, Date birthday, String base64, List<String> added, List<String> liked) {
        this.pseudo = pseudo;
        this.name = name;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.base64 = base64;
        this.added = added;
        this.liked = liked;
    }

    public User() {
        this.pseudo = "";
        this.name = "";
        this.firstName = "";
        this.birthday = new Date();
        this.base64 = "";
        this.added = new ArrayList<>();
        this.liked = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getPseudo() {
        return pseudo;
    }

    public User setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getBase64() {
        return base64;
    }

    public User setBase64(String base64) {
        this.base64 = base64;
        return this;
    }

    public List<String> getAdded() {
        return added;
    }

    public User setAdded(List<String> added) {
        this.added = added;
        return this;
    }

    public List<String> getLiked() {
        return liked;
    }

    public User setLiked(List<String> liked) {
        this.liked = liked;
        return this;
    }
}
