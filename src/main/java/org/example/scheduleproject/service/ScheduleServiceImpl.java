package org.example.scheduleproject.service;


import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //일정 생성
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        //날짜를 포함한 새 일정 생성
        Schedule schedule = new Schedule(dto.getTodo(), dto.getAuthor(), dto.getPassword());
        //레포지토리로 전달
        return scheduleRepository.saveSchedule(schedule);
    }

    //id로 일정 조회
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByFilter(Optional<String> author, Optional<Date> date) {
        return scheduleRepository.findScheduleByFilter(author, date);
    }
}
