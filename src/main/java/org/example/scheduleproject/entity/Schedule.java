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
    private Date createDate;
    private Date editDate;

    public Schedule (String todo, String author, String password){
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.createDate = new Date();
        this.editDate = createDate;
    }

    public Schedule(Long id, String todo, String author, Date createDate, Date editDate) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.createDate = createDate;
        this.editDate = editDate;
    }
}
