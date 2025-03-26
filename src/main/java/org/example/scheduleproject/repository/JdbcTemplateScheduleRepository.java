package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.server.ResponseStatusException;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        parameters.put("author_id", schedule.getAuthorId());
        parameters.put("create_date", schedule.getCreateDate());
        parameters.put("edit_date", schedule.getEditDate());
        parameters.put("password",schedule.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        //새로 만들어진 일정 반환
        return new ScheduleResponseDto(key.longValue(), schedule.getTodo(), schedule.getCreateDate(), schedule.getEditDate(), schedule.getAuthorId(), schedule.getAuthorName());
    }

    //id로 일정 조회
    @Override
    public Schedule findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select s.*, a.name from schedule s left join author a on s.author_id = a.author_id where s.id = ?", scheduleRowMapperV2(), id);
        //없을 경우 바로 404 에러
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist = " + id));
    }

    //작성자 id, 날짜 필터
    @Override
    public List<ScheduleResponseDto> findScheduleByFilter(Optional<Long> authorId, Optional<Date> date) {
        String query = "select  s.*, a.name from schedule s left join author a on s.author_id = a.author_id  ";
        List<ScheduleResponseDto> result;

        if(authorId.isPresent() && date.isEmpty()){
            query += "where s.author_id = ? order by s.edit_date";
            result = jdbcTemplate.query(query, scheduleRowMapper(), authorId.get());
        } else if(authorId.isEmpty() && date.isPresent()){
            query += "where date(s.edit_date) = ? order by s.edit_date";
            result = jdbcTemplate.query(query, scheduleRowMapper(), date.get());
        } else if(authorId.isPresent() && date.isPresent()) {
            query += "where s.author_id = ? AND date(s.edit_date) = ? order by s.edit_date";
            result = jdbcTemplate.query(query, scheduleRowMapper(), authorId.get(), date.get());
        } else {
            //날짜와 작성자 id 둘 다 들어오지 않으면 전체 결과 반환
            query+="order by edit_date";
            result = jdbcTemplate.query(query, scheduleRowMapper());
        }
        return result;
    }

    //일정 수정 (작성자 id, 할일, 수정일)
    @Override
    public int updateSchedule(Long id, String todo, Long authorId, String password) {
        return jdbcTemplate.update("update schedule set todo = ?, author_id = ?, edit_date = CURRENT_TIMESTAMP where id = ? and password = ?", todo, authorId, id, password);
    }

    //일정 삭제
    @Override
    public int deleteSchedule(Long id, String password) {
        return jdbcTemplate.update("delete from schedule where id = ? and password = ?", id, password);
    }

    //Mapper (ScheduleResponseDto)
    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getTimestamp("create_date"),
                        rs.getTimestamp("edit_date"),
                        rs.getLong("author_id"),
                        rs.getString("name")
                );
            }
        };
    }

    //Mapper (Schedule)
    private RowMapper<Schedule> scheduleRowMapperV2(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getLong("author_id"),
                        rs.getTimestamp("create_date"),
                        rs.getTimestamp("edit_date"),
                        rs.getString("name")
                );
            }
        };
    }
}


