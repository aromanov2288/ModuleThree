package ru.romanov.modulethree.dao;

import ru.romanov.modulethree.domain.Book;

import java.util.List;

public interface BookDao {

    int getCount();

    void insert(String bookName, int authorId, int genreId);

    Book getById(int id);

    List<Book> getAll();

    void update(Book Book);

    void deleteByIb(int id);
}
