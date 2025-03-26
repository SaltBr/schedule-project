package org.example.scheduleproject.service;


import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        Schedule schedule = new Schedule(dto.getTodo(), dto.getAuthorId(), dto.getPassword());
        //레포지토리로 전달
        return scheduleRepository.saveSchedule(schedule);
    }

    //id로 일정 조회
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);
        return new ScheduleResponseDto(schedule);
    }

    //작성자 id, 날짜 필터
    @Override
    public List<ScheduleResponseDto> findScheduleByFilter(Optional<Long> authorId, Optional<Date> date) {
        return scheduleRepository.findScheduleByFilter(authorId, date);
    }

    //일정 수정 및 오류 관리
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String todo, Long authorId, String password) {
        if(todo == null || authorId == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Todo and author value is required.");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, todo, authorId, password);

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist, or password is incorrect.");
        }

        Schedule schedule = scheduleRepository.findScheduleById(id);
        return new ScheduleResponseDto(schedule);    }

    //일정 삭제 및 오류 관리
    @Override
    public void deleteSchedule(Long id, String password) {
        int deletedRow = scheduleRepository.deleteSchedule(id, password);
        if(deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist, or password is incorrect.");
        }
    }
}
