package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.AuthorRequestDto;
import org.example.scheduleproject.dto.AuthorResponseDto;
import org.example.scheduleproject.entity.Author;
import org.example.scheduleproject.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //작성자 생성
    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
        Author author = new Author(dto.getName(), dto.getEmail());
        return authorRepository.createAuthor(author);
    }

    //id로 작성자 조회
    @Override
    public AuthorResponseDto findAuthorById(Long authorId) {
        Author author = authorRepository.findAuthorById(authorId);
        return new AuthorResponseDto(author);
    }

    //작성자 이름, 이메일 수
    @Transactional
    @Override
    public AuthorResponseDto updateAuthor(Long authorId, String name, String email) {
        if (name == null || email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name and email values are required.");
        }

        int updatedRow = authorRepository.updateAuthor(authorId, name, email);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist: " + authorId);
        }

        Author author = authorRepository.findAuthorById(authorId);
        return new AuthorResponseDto(author);
    }
}
