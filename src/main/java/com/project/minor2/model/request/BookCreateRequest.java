package com.project.minor2.model.request;

import com.project.minor2.model.Author;
import com.project.minor2.model.Book;
import com.project.minor2.model.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {
    @NotBlank
    private String name;

    @NotNull
    private Author author;

    @Positive
    private int cost;

    @NotNull
    private BookGenre genre;

    public Book toBook(){
        return Book.builder()
                .cost(this.cost)
                .genre(this.genre)
                .name(this.name)
                .author(this.author)
                .build();
    }
}
