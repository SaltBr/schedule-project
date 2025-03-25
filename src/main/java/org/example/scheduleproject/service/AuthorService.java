package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.AuthorRequestDto;
import org.example.scheduleproject.dto.AuthorResponseDto;

public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorRequestDto dto);
    AuthorResponseDto findAuthorById(Long authorId);
}
