package com.moovie.moovieemail.Model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Email")

public class Email {

    private String toSend;

    private String title;

    private String message;

    public Email() {
    }

    public Email(String toSend, String title, String message) {
        this.toSend = toSend;
        this.title = title;
        this.message = message;
    }

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
