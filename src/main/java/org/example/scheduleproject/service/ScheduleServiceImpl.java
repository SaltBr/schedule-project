package org.example.scheduleproject.service;


import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService {
   private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTodo(), dto.getAuthor(), dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return null;
    }

    @Override
    public ScheduleResponseDto findScheduleByAuthor(String author) {
        return null;
    }

    @Override
    public ScheduleResponseDto findScheduleByEditDate(Date edit_date) {
        return null;
    }
}
