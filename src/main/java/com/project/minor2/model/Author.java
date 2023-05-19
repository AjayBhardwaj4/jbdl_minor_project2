package com.project.minor2.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "country_land")
    private String country;
    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;

    @CreationTimestamp
    private Date addedOn;
}
