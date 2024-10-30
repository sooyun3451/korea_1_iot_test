package com.example.springboot_test.controller;

import com.example.springboot_test.common.constant.ApiMappingPattern;
import com.example.springboot_test.dto.request.PostBookRequestDto;
import com.example.springboot_test.dto.request.UpdateBookRequestDto;
import com.example.springboot_test.dto.response.*;
import com.example.springboot_test.entity.Category;
import com.example.springboot_test.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BOOK)
@RequiredArgsConstructor
public class BookController {

    public static final String BOOK_GET_LIST = "/list";
    public static final String BOOK_GET_BOOK_ID = "/{id}";
    public static final String BOOK_GET_BY_CATEGORY = "/search/category";
    public static final String BOOK_POST = "/";
    public static final String BOOK_PUT = "/{id}";
    public static final String BOOK_DELETE = "/{id}";

    private final BookService bookService;

    @GetMapping(BOOK_GET_BOOK_ID)
    public ResponseEntity<ResponseDto<GetBookResponseDto>> getBook(@PathVariable Long id) {
         ResponseDto<GetBookResponseDto> result = bookService.getBook(id);
         return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(BOOK_GET_LIST)
    public ResponseEntity<ResponseDto<List<GetBookListResponseDto>>> getAllBooks() {
        ResponseDto<List<GetBookListResponseDto>> result = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(BOOK_GET_BY_CATEGORY)
    public ResponseEntity<ResponseDto<List<GetBookListResponseDto>>> getBooksByCategory(@RequestParam Category category) {
        ResponseDto<List<GetBookListResponseDto>> result = bookService.getBooksByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(BOOK_POST)
    public ResponseEntity<ResponseDto<PostBookResponseDto>> createBook(@RequestBody PostBookRequestDto dto) {
        ResponseDto<PostBookResponseDto> result = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(BOOK_PUT)
    public ResponseEntity<ResponseDto<UpdateBookResponseDto>> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequestDto dto) {
        ResponseDto<UpdateBookResponseDto> result = bookService.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(BOOK_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteBook(@PathVariable Long id) {
        ResponseDto<Void> result = bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}