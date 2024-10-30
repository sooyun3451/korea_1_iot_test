package com.example.springboot_test.dto.request;

import com.example.springboot_test.entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostBookRequestDto {
    @NotNull
    private String bookTitle;
    @NotNull
    private String bookAuthor;
    @NotNull
    private int bookPublicationYear;
    @NotNull
    private Category category;
}
