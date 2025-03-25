package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    Schedule findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleByFilter(Optional<String> author, Optional<Date> editDate);
    int updateSchedule(Long id, String todo, String author, String password);
    int deleteSchedule(Long id, String password);
}
