package org.example.scheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleproject.entity.Schedule;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String todo;
    private String author;
    private Date createDate;
    private Date editDate;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.createDate = schedule.getCreateDate();
        this.editDate = schedule.getEditDate();
    }
}
