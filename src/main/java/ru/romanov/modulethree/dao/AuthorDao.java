package ru.romanov.modulethree.dao;

import ru.romanov.modulethree.domain.Author;

import java.util.List;

public interface AuthorDao {

    int insert(Author author);

    Author getById(int id);

    List<Author> getAll();

    void update(int id, String fio);

    void deleteById(int id);
}
