package com.project.minor2.service;

import com.project.minor2.model.Author;
import com.project.minor2.model.Book;
import com.project.minor2.model.BookGenre;
import com.project.minor2.model.request.BookCreateRequest;
import com.project.minor2.model.request.BookFilterType;
import com.project.minor2.repository.AuthorRepository;
import com.project.minor2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public void create(BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.toBook();
        createOrUpdate(book);
    }

    public void createOrUpdate(Book book) {
        Author author = book.getAuthor();

        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        if(authorFromDB == null){
            authorFromDB = authorRepository.save(author);
        }

        book.setAuthor(authorFromDB);
        bookRepository.save(book);
    }

    public List<Book> find(BookFilterType bookFilterType, String value) {
        switch (bookFilterType) {
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthor_Name(value);
            case GENRE:
                return bookRepository.findByGenre(BookGenre.valueOf(value));
            case BOOK_ID:
                return bookRepository.findAllById(Collections.singletonList(Integer.parseInt(value)));
            default:
                return new ArrayList<>();
        }
    }
}
