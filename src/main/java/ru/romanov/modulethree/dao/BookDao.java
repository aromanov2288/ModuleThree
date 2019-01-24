package ru.romanov.modulethree.dao;

import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Genre;

import java.util.List;
import java.util.Set;

public interface BookDao {

    int insert(Book book);

    Book getById(int id);

    List<Book> getAll();

    void update(int id, String name, Genre genre, Set<Author> authorsSet);

    void deleteById(int id);
}
