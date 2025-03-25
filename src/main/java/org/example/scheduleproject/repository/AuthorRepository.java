package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.AuthorResponseDto;
import org.example.scheduleproject.entity.Author;

public interface AuthorRepository {
    AuthorResponseDto createAuthor(Author author);
    Author findAuthorById(Long authorId);
    int updateAuthor(Long authorId, String name, String email);

}
