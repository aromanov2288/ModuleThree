package ru.romanov.modulethree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.modulethree.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment save(Comment comment);

    Optional<Comment> findById(Integer Id);

    List<Comment> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE Comment c SET c.text = :text WHERE c.id = :id")
    void update(@Param("id") Integer id, @Param("text") String text);

    void deleteById(Integer id);
}
