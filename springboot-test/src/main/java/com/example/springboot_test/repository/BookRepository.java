package com.example.springboot_test.repository;

import com.example.springboot_test.entity.Book;
import com.example.springboot_test.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategory(Category category);

}
