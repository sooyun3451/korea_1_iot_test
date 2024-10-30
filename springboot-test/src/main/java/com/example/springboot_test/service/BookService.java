package com.example.springboot_test.service;

import com.example.springboot_test.common.constant.ResponseMessage;
import com.example.springboot_test.dto.request.PostBookRequestDto;
import com.example.springboot_test.dto.request.UpdateBookRequestDto;
import com.example.springboot_test.dto.response.*;
import com.example.springboot_test.entity.Book;
import com.example.springboot_test.entity.Category;
import com.example.springboot_test.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 단건 조회
    public ResponseDto<GetBookResponseDto> getBook(Long id) {
        GetBookResponseDto data = null;
        Long bookId = id;

        try {
            Optional<Book> bookOptional = bookRepository.findById(bookId);

            if(bookOptional.isPresent()) {
                data = new GetBookResponseDto(bookOptional.get());
            }else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

        } catch(Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 전체 조회
    public ResponseDto<List<GetBookListResponseDto>> getAllBooks() {
        List<GetBookListResponseDto> data = null;
        try {
            List<Book> books = bookRepository.findAll();

            data = books.stream()
                    .map((book) -> new GetBookListResponseDto(book))
                    .collect(Collectors.toList());

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 제목으로 책 찾아오기
    public ResponseDto<List<GetBookListResponseDto>> getBooksByCategory(Category category) {
        List<GetBookListResponseDto> data = null;

        try {
            List<Book> books = bookRepository.findByCategory(category);
            data = books.stream()
                    .map((book) -> new GetBookListResponseDto(book))
                    .collect(Collectors.toList());

        } catch(Exception e) {
            e.printStackTrace();
            ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 책 생성
    public ResponseDto<PostBookResponseDto> createBook(PostBookRequestDto dto) {
        PostBookResponseDto data = null;

        try {
            Book book = Book.builder()
                    .book_title(dto.getBookTitle())
                    .book_author(dto.getBookAuthor())
                    .book_publication_year(dto.getBookPublicationYear())
                    .category(dto.getCategory())
                    .build();

            bookRepository.save(book);
            data = new PostBookResponseDto(book);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 특정 ID 업데이트
    public ResponseDto<UpdateBookResponseDto> updateBook(Long id, UpdateBookRequestDto dto) {
        UpdateBookResponseDto data = null;
        Long bookId = id;

        try {
            Optional<Book> bookOptional = bookRepository.findById(bookId);

            if(bookOptional.isPresent()) {
                Book book = bookOptional.get();
                book.setBook_title(dto.getBookTitle());
                book.setBook_author(dto.getBookAuthor());
                book.setBook_publication_year(dto.getBookPublicationYear());
                book.setCategory(dto.getCategory());

                bookRepository.save(book);
                data = new UpdateBookResponseDto(book);
            }

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 특정 ID 삭제
    public ResponseDto<Void> deleteBook(Long id) {
        Long bookId = id;

        try {
            if(!bookRepository.existsById(bookId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_POST);
            }
            bookRepository.deleteById(bookId);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
