package ru.romanov.modulethree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.modulethree.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre save(Genre genre);

    Optional<Genre> findById(Integer Id);

    List<Genre> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Genre g SET g.name = :name WHERE g.id = :id")
    void update(@Param("id") Integer id, @Param("name") String name);

    void deleteById(Integer id);
}
