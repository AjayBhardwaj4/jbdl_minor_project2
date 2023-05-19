package com.project.minor2.repository;

import com.project.minor2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByEmail(String email);

    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(int age, String country, String prefix);

    @Query(value = "select a from author a where a.email = ?1", nativeQuery = true)
    public Author getAuthorGivenEmailIdNativeQuery(String author_email);

    @Query(value = "select a from Author a where a.email = ?1")
    public Author getAuthorGivenEmailIdJPQL(String author_email);

    @Query(value = "select a from author a where a.land = :country", nativeQuery = true)
    public List<Author> getAuthorsByCountry(String country);

    @Query(value = "select a from Author a where a.country = :country")
    public List<Author> getAuthorsByCountryJPQL(String country);

}
