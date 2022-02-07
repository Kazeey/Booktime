package com.project.booktime.model.entity;

import org.json.simple.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("book")
public class Book {

    @Id
    private String id;
    private String title;
    private String synopsis;
    private Object ISBN;
    private Date publicationDate;
    private Object category;
    private String pageCount;
    private String rating;
    private String authorId;
    private String base64;

    public Book(String title, String synopsis, Object ISBN, Date publicationDate, Object category, String pageCount, String rating, String authorId, String base64) {
        this.title = title;
        this.synopsis = synopsis;
        this.ISBN = ISBN;
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

    public Object getISBN() {
        return ISBN;
    }

    public void setISBN(Object ISBN) {
        this.ISBN = ISBN;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
