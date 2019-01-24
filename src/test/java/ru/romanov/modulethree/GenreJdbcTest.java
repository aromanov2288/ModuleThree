package ru.romanov.modulethree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.romanov.modulethree.dao.GenreDao;
import ru.romanov.modulethree.dao.GenreJdbc;
import ru.romanov.modulethree.domain.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(GenreJdbc.class)
public class GenreJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void getCountTest() {
        assertEquals(genreDao.getCount(), 3);
    }

    @Test
    public void insertTest() {
        genreDao.insert("Detekriv");
        assertEquals(genreDao.getCount(), 4);
    }

    @Test
    public void getByIdTest() {
        Genre genre = genreDao.getById(2);
        assertEquals(genre.getGenreName(), "Stihotvorenie");
    }

    @Test
    public void getAllTest() {
        List<Genre> genreList = genreDao.getAll();
        assertEquals(genreList.size(), 3);
    }

    @Test
    public void updateTest() {
        Genre newGenre = new Genre(1, "Novella");
        genreDao.update(newGenre);
        Genre updatedGenre = genreDao.getById(1);
        assertEquals(updatedGenre.getGenreName(), "Novella");
    }

    @Test
    public void deleteById() {
        genreDao.deleteByIb(3);
        assertEquals(genreDao.getCount(), 2);
    }
}

