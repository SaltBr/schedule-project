package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.AuthorResponseDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Author;
import org.example.scheduleproject.entity.Schedule;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateAuthorRepository implements AuthorRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //작성자 생성
    @Override
    public AuthorResponseDto createAuthor(Author author) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("author").usingGeneratedKeyColumns("authorId");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", author.getName());
        parameters.put("email", author.getEmail());
        parameters.put("create_date", author.getCreateDate());
        parameters.put("edit_date", author.getEditDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new AuthorResponseDto(key.longValue(), author.getName(), author.getEmail(), author.getCreateDate(), author.getEditDate());
    }

    //id로 작성자 조회
    @Override
    public Author findAuthorById(Long authorId) {
        List<Author> result = jdbcTemplate.query("select * from author where author_id = ?", authorRowMapperV2(), authorId);
        //없을 경우 바로 404 에러
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist = " + authorId));
    }


    private RowMapper<AuthorResponseDto> authorRowMapper(){
        return new RowMapper<AuthorResponseDto>() {
            @Override
            public AuthorResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new AuthorResponseDto(
                        rs.getLong("author_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("create_date"),
                        rs.getTimestamp("edit_date")
                );
            }
        };
    }

    private RowMapper<Author> authorRowMapperV2(){
        return new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Author(
                        rs.getLong("author_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("create_date"),
                        rs.getTimestamp("edit_date")
                );
            }
        };
    }
}
