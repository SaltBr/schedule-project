package org.example.scheduleproject.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class AuthorRequestDto {

    private Long authorId;
    private String name;
    private String email;
    private Date createDate;
    private Date editDate;
}