package org.example.scheduleproject.dto;

import lombok.Getter;

import jakarta.validation.constraints.Email;
import java.util.Date;

@Getter
public class AuthorRequestDto {

    private Long authorId;
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    private Date createDate;
    private Date editDate;
}