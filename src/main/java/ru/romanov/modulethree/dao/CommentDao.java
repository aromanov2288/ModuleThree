package ru.romanov.modulethree.dao;

import ru.romanov.modulethree.domain.Comment;

import java.util.List;

public interface CommentDao {

    int insert(Comment comment);

    Comment getById(int id);

    List<Comment> getAll();

    void update(int id, String text);

    void deleteById(int id);
}
