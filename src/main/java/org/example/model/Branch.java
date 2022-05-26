package org.example.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "branches")
public class Branch {
    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    @Getter
    @Setter
    @Column(name = "authorId")
    private int authorId;


    public Branch(int id, String name, int authorId) {
        this.id = id;
        this.authorId =authorId;
        this.name = name;

    }
    public Branch(){

    }



}
