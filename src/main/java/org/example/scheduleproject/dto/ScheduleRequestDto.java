package org.example.scheduleproject.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private String todo;
    private String author;
    private String password;
    private Date createDate;
    private Date editDate;

}
