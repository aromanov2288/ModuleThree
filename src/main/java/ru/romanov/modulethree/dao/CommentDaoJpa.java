package ru.romanov.modulethree.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.modulethree.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public int insert(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

    @Override
    public Comment getById(int id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id, String text) {
        Comment comment = em.find(Comment.class, id);
        comment.setText(text);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }
}
