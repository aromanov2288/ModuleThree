package ru.romanov.modulethree.dao;

import ru.romanov.modulethree.domain.Genre;

import java.util.List;

public interface GenreDao {

    int getCount();

    void insert(String name);

    Genre getById(int id);

    List<Genre> getAll();

    void update(Genre genre);

    void deleteByIb(int id);
}
