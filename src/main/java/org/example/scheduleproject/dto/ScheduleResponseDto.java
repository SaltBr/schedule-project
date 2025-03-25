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
    private Date createDate;
    private Date editDate;
    private Long authorId;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.createDate = schedule.getCreateDate();
        this.editDate = schedule.getEditDate();
        this.authorId = schedule.getAuthorId();
    }
}
