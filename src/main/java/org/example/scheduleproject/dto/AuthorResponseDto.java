package org.example.scheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleproject.entity.Author;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AuthorResponseDto {

    private Long authorId;
    private String name;
    private String email;
    private Date createDate;
    private Date editDate;

    public AuthorResponseDto(Author author){
        this.authorId = author.getAuthorId();
        this.name = author.getName();
        this.email = author.getEmail();
        this.createDate = author.getCreateDate();
        this.editDate = author.getEditDate();
    }
}