package com.project.booktime.model.dto;

import java.util.List;

public class LibraryDTO {

    private String id;
    private String userId;
    private List<String> bookIdList;

    public LibraryDTO(String id, String userId, List<String> bookIdList) {
        this.id = id;
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
