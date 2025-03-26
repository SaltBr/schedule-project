package org.example.scheduleproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.example.scheduleproject.entity.Schedule;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    @NotNull
    @Size(max = 200, message = "Todo is too long")
    private String todo;
    @NotNull
    private String password;
    private Date createDate;
    private Date editDate;
    private Long authorId;

}
