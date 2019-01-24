package ru.romanov.modulethree.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.modulethree.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public int insert(Genre genre) {
        em.persist(genre);
        return genre.getId();
    }

    @Override
    public Genre getById(int id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id, String name) {
        Genre genre = em.find(Genre.class, id);
        genre.setName(name);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Genre genre = em.find(Genre.class, id);
        em.remove(genre);
    }
}
