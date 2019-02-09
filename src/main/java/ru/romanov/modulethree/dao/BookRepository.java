package ru.romanov.modulethree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanov.modulethree.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book save(Book book);

    Optional<Book> findById(Integer Id);

    List<Book> findAll();

    void deleteById(Integer id);
}
