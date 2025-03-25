package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor

public class Author {
    private Long authorId;
    private String name;
    private String email;
    private Date createDate;
    private Date editDate;

    //작성자 생성자
    public Author (String name, String email){
        this.name = name;
        this.email = email;
        this.createDate = new Date();
        this.editDate = createDate;
    }
}
