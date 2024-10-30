package com.example.springboot_test.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String book_title;

    @Column(nullable = false, length = 100)
    private String book_author;

    @Column(nullable = false)
    private int book_publication_year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("기타")
    private Category category;
}
