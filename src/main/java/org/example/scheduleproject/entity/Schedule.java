package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor

public class Schedule {
    private Long id;
    private String todo;
    private String password;
    private Date createDate;
    private Date editDate;
    private Long authorId;
    private String authorName;

    //처음 일정 생성시 사용하는 생성자
    //비밀번호 포함, 생성일 지정
    public Schedule (String todo, Long authorId, String password){
        this.todo = todo;
        this.authorId = authorId;
        this.password = password;
        this.createDate = new Date();
        this.editDate = createDate;
    }

    //비밀번호 제외한 생성자
    public Schedule(Long id, String todo, Long authorId, Date createDate, Date editDate, String authorName) {
        this.id = id;
        this.todo = todo;
        this.authorId = authorId;
        this.createDate = createDate;
        this.editDate = editDate;
        this.authorName = authorName;
    }
}
