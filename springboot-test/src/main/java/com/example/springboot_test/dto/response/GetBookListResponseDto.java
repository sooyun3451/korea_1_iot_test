package com.example.springboot_test.dto.response;

import com.example.springboot_test.entity.Book;
import com.example.springboot_test.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBookListResponseDto {
    private Long id;
    private String bookTitle;
    private String bookAuthor;
    private int bookPublicationYear;
    private Category category;

    public GetBookListResponseDto(Book book) {
        this.id = book.getId();
        this.bookTitle = book.getBook_title();
        this.bookAuthor = book.getBook_author();
        this.bookPublicationYear = book.getBook_publication_year();
        this.category = book.getCategory();
    }
}
