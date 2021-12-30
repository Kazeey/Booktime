package com.project.booktime.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("library")
public class Library {

    @Id
    private String id;
    private String userId;
    private List<String> bookIdList;

    public Library(String userId, List<String> bookIdList) {
        this.userId = userId;
        this.bookIdList = bookIdList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getBookIdList() {
        return bookIdList;
    }

    public void setBookIdList(List<String> bookIdList) {
        this.bookIdList = bookIdList;
    }
}
