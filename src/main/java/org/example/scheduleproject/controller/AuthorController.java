package org.example.scheduleproject.controller;

import org.example.scheduleproject.dto.AuthorRequestDto;
import org.example.scheduleproject.dto.AuthorResponseDto;
import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@RequestBody AuthorRequestDto dto) {
        return new ResponseEntity<>(authorService.createAuthor(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponseDto> findAuthorById(@PathVariable Long authorId){
        return new ResponseEntity<>(authorService.findAuthorById(authorId), HttpStatus.OK);
    }
}
