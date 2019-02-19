package ru.romanov.modulethree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.modulethree.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author save(Author author);

    Optional<Author> findById(Integer Id);

    List<Author> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Author a SET a.fio = :fio WHERE a.id = :id")
    void update(@Param("id") Integer id, @Param("fio") String fio);

    void deleteById(Integer id);
}
