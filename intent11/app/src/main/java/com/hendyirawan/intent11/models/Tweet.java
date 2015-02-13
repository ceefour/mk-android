package com.hendyirawan.intent11.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ceefour on 2/12/15.
 */
public class Tweet implements Serializable {

    private Long id;
    private String title;
    private String body;
    private Date publishedDate;

    public Tweet(Long id, String title, String body, Date publishedDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}
