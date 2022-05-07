package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Branch {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int author;


    public Branch(int id, String name, int author) {
        this.id = id;
        this.author=author;
        this.name = name;

    }
    public Branch(){

    }



}
