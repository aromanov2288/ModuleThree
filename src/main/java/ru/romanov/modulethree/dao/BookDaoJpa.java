package ru.romanov.modulethree.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public int insert(Book book) {
        em.persist(book);
        return book.getId();
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id, String name, Genre genre, Set<Author> authorsSet) {
        Book book = em.find(Book.class, id);
        book.setName(name);
        book.setGenre(genre);
        book.setAuthorsSet(authorsSet);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }
}
