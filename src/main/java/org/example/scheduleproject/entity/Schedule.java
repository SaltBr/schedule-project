package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor

public class Schedule {
    private Long id;
    private String todo;
    private String author;
    private String password;
    private Date create_date;
    private Date edit_date;

    public Schedule (String todo, String author, String password){
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.create_date = new Date();
        this.edit_date = create_date;
    }
}
