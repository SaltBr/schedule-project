package org.example.scheduleproject.controller;

import jakarta.validation.Valid;
import org.example.scheduleproject.dto.AuthorRequestDto;
import org.example.scheduleproject.dto.AuthorResponseDto;
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

    //작성자 생성
    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto dto) {
        return new ResponseEntity<>(authorService.createAuthor(dto), HttpStatus.CREATED);
    }

    //id로 작성자 조회
    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponseDto> findAuthorById(@PathVariable Long authorId){
        return new ResponseEntity<>(authorService.findAuthorById(authorId), HttpStatus.OK);
    }

    //작성자 이름, 이메일 수정
    @PatchMapping("/{authorId}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @PathVariable Long authorId,
            @RequestBody AuthorRequestDto dto
    ) {
        return new ResponseEntity<>(authorService.updateAuthor(authorId, dto.getName(), dto.getEmail()), HttpStatus.OK);
    }
}
