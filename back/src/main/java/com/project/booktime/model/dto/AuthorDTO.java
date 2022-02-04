package com.project.booktime.model.dto;

import java.util.Date;

public class AuthorDTO
{
    private String id;
    private String name;
    private String firstName;
    private Date birthDate;
    private Date deathDate;
    private String biography;
    private String country;
    private String base64;

    public AuthorDTO(String id, String name, String firstName, Date birthDate, Date deathDate, String biography, String country, String base64)
    {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.biography = biography;
        this.country = country;
        this.base64 = base64;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
