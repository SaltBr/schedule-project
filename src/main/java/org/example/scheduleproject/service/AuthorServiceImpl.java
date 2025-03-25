package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.AuthorRequestDto;
import org.example.scheduleproject.dto.AuthorResponseDto;
import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Author;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.repository.AuthorRepository;
import org.example.scheduleproject.repository.JdbcTemplateAuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //새 작성자 생성
    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
        Author author = new Author(dto.getName(), dto.getEmail());
        return authorRepository.createAuthor(author);
    }

    @Override
    public AuthorResponseDto findAuthorById(Long authorId) {
        Author author = authorRepository.findAuthorById(authorId);
        return new AuthorResponseDto(author);
    }
}
