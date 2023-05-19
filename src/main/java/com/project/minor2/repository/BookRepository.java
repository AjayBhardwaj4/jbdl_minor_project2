package com.project.minor2.repository;

import com.project.minor2.model.Book;
import com.project.minor2.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByName(String name);

    List<Book> findByAuthor_Name(String authorName);

    List<Book> findByGenre(BookGenre genre);
}