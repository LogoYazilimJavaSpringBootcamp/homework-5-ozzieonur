package com.moovie.moovienetwork.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class EmailDto {

    private String toSend;

    private String title;

    private String message;

    public EmailDto() {
    }

    public EmailDto(String toSend, String title, String message) {
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
