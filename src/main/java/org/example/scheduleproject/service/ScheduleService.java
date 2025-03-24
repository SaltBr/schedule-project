package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto findScheduleByAuthor(String author);
    ScheduleResponseDto findScheduleByEditDate(java.util.Date edit_date);
}
