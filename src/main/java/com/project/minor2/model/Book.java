package com.project.minor2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.minor2.model.response.BookSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int cost;

    @Enumerated(value = EnumType.STRING)
    private BookGenre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("bookList")
    private Author author;

    @ManyToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    public BookSearchResponse toBookSearchResponse() {
        return BookSearchResponse.builder()
                .id(id)
                .name(name)
                .cost(cost)
                .genre(genre)
                .author(author)
                .student(student)
                .transactionList(transactionList)
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .build();
    }

}
