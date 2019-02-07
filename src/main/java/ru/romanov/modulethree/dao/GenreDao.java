package ru.romanov.modulethree.dao;

import ru.romanov.modulethree.domain.Genre;

import java.util.List;

public interface GenreDao {

    int insert(Genre genre);

    Genre getById(int id);

    List<Genre> getAll();

    void update(int id, String name);

    void deleteById(int id);
}
