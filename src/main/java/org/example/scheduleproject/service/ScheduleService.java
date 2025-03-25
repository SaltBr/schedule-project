package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleByFilter(Optional<String> author, Optional<Date> date);
    ScheduleResponseDto updateSchedule(Long id, String todo, String author, String password);
    void deleteSchedule(Long id, String password);
}
