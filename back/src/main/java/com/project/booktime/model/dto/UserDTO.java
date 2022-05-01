package com.project.booktime.model.dto;

import java.util.Date;
import java.util.List;

public class UserDTO {

    private String id;
    private String pseudo;
    private String name;
    private String firstName;
    private String email;
    private String password;
    private Date birthday;
    private String base64;
    private List<String> library;
    private List<String> liked;

    public UserDTO(String id, String pseudo, String name, String firstName, String email, Date birthday, String base64, List<String> library, List<String> liked) {
        this.id = id;
        this.pseudo = pseudo;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.base64 = base64;
        this.library = library;
        this.liked = liked;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<String> getLibrary() {
        return library;
    }

    public void setLibrary(List<String> library) {
        this.library = library;
    }

    public List<String> getLiked() {
        return liked;
    }

    public void setLiked(List<String> liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", library=" + library +
                ", liked=" + liked +
                '}';
    }
}
