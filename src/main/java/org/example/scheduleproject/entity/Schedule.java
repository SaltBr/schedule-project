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

    //처음 일정 생성시 사용하는 생성자
    //비밀번호 포함, 생성일 지정
    public Schedule (String todo, String author, String password){
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.createDate = new Date();
        this.editDate = createDate;
    }

    //비밀번호 제외한 생성자
    public Schedule(Long id, String todo, String author, Date createDate, Date editDate) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.createDate = createDate;
        this.editDate = editDate;
    }
}
