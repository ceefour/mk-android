package com.hendyirawan.asynctask10.models;

import java.io.Serializable;

/**
 * Created by ceefour on 2/12/15.
 */
public class Tweet implements Serializable {

    private Long id;
    private String title;
    private String body;

    public Tweet(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
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
}
