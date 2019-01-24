package ru.romanov.modulethree.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.modulethree.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public int insert(Author author) {
        em.persist(author);
        return author.getId();
    }

    @Override
    public Author getById(int id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id, String fio) {
        Author author = em.find(Author.class, id);
        author.setFio(fio);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Author author = em.find(Author.class, id);
        em.remove(author);
    }
}
