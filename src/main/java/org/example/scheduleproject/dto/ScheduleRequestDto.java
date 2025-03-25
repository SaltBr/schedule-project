package org.example.scheduleproject.dto;

import lombok.Getter;
import org.example.scheduleproject.entity.Schedule;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private String todo;
    private String password;
    private Date createDate;
    private Date editDate;
    private Long authorId;
    private String authorName;

}
