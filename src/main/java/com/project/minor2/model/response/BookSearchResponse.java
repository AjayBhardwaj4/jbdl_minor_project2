package com.project.minor2.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.minor2.model.Author;
import com.project.minor2.model.BookGenre;
import com.project.minor2.model.Student;
import com.project.minor2.model.Transaction;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookSearchResponse {
    private int id;
    private String name;
    private int cost;
    private BookGenre genre;

    @JsonIgnoreProperties("bookList")
    private Author author;

    private Student student;
    private List<Transaction> transactionList;
    private Date createdOn;
    private Date updatedOn;
}
