package com.project.booktime.model.dto;

import java.util.Date;

public class BookDTO {

    private String id;
    private String title;
    private String synopsis;
    private Date publicationDate;
    private String category;
    private int pageCount;
    private double rating;
    private String authorId;
    private String base64;

    public BookDTO(String id, String title, String synopsis, Date publicationDate, String category, int pageCount, double rating, String authorId, String base64) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.publicationDate = publicationDate;
        this.category = category;
        this.pageCount = pageCount;
        this.rating = rating;
        this.authorId = authorId;
        this.base64 = base64;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
