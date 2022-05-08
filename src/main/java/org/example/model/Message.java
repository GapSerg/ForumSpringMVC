package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Message {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private Date date;
    @Getter
    @Setter
    private User author;
    @Getter
    @Setter
    private Branch branch;

    public Message() {

    }

    public Message(int id, String text, Date date, User author, Branch branch) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.author = author;
        this.branch = branch;
    }

}
