package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.server.ResponseStatusException;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //일정 생성 후 데이터베이스에 저장
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        //id 기준으로 데이터베이스에 추가
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", schedule.getTodo());
        parameters.put("author", schedule.getAuthor());
        parameters.put("createDate", schedule.getCreateDate());
        parameters.put("editDate", schedule.getEditDate());
        parameters.put("password",schedule.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        //새로 만들어진 일정 반환
        return new ScheduleResponseDto(key.longValue(), schedule.getTodo(), schedule.getAuthor(), schedule.getCreateDate(), schedule.getEditDate());
    }

    //id로 일정 조회
    @Override
    public Schedule findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        //없을 경우 바로 404 에러
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist = " + id));
    }

    //작성자명, 날짜 필터
    @Override
    public List<ScheduleResponseDto> findScheduleByFilter(Optional<String> author, Optional<Date> date) {
        String query = "select * from schedule ";
        List<ScheduleResponseDto> result;

        if(author.isPresent() && date.isEmpty()){
            query += "where author = ? order by editDate";
            result = jdbcTemplate.query(query, scheduleRowMapper(), author.get());
        } else if(author.isEmpty() && date.isPresent()){
            query += "where date(editDate) = ? order by editDate";
            result = jdbcTemplate.query(query, scheduleRowMapper(), date.get());
        } else if(author.isPresent() && date.isPresent()) {
            query += "where author = ? AND date(editDate) = ? order by editDate";
            result = jdbcTemplate.query(query, scheduleRowMapper(), author.get(), date.get());
        } else {
            //날짜와 작성자명이 둘 다 들어오지 않으면 전체 결과 반환
            query+="order by editDate";
            result = jdbcTemplate.query(query, scheduleRowMapper());
        }
        return result;
    }


    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("author"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("editDate")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("author"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("editDate")
                );
            }
        };
    }
}


