package ru.romanov.modulethree.dao;

import ru.romanov.modulethree.domain.Author;

import java.util.List;

public interface AuthorDao {

    int getCount();

    void insert(String name);

    Author getById(int id);

    List<Author> getAll();

    void update(Author author);

    void deleteByIb(int id);
}
