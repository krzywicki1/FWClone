package com.example.p2.repository;


import com.example.p2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByOrcid(String orcid);

    // ILIKE '%ski'
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM AUTHOR " +
            "WHERE LAST_NAME ILIKE :pattern " +
            "ORDER BY ID")
    List<Author> findAllWithLastNameMatching(@Param("pattern") String pattern);
}