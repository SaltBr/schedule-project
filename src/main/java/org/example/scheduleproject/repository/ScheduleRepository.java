package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    Schedule findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleByAuthor();
    List<ScheduleResponseDto> findScheduleByEditDate();

}
