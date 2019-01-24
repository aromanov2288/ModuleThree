package ru.romanov.modulethree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.romanov.modulethree.dao.AuthorDao;
import ru.romanov.modulethree.dao.AuthorDaoJpa;
import ru.romanov.modulethree.dao.BookDao;
import ru.romanov.modulethree.dao.BookDaoJpa;
import ru.romanov.modulethree.dao.CommentDao;
import ru.romanov.modulethree.dao.CommentDaoJpa;
import ru.romanov.modulethree.dao.GenreDao;
import ru.romanov.modulethree.dao.GenreDaoJpa;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Comment;
import ru.romanov.modulethree.domain.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({AuthorDaoJpa.class, BookDaoJpa.class, CommentDaoJpa.class, GenreDaoJpa.class})
public class GenreDaoJpaTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private GenreDao genreDao;

    @Test
    public void insertTest() {
        int id = genreDao.insert(new Genre("Detektiv"));
        Genre genreFromDB = genreDao.getById(id);
        assertEquals(genreFromDB.getName(), "Detektiv");
    }

    @Test
    public void getByIdTest() {
        Genre genreFromDB = genreDao.getById(1);
        assertEquals(genreFromDB.getName(), "Povest");
    }

    @Test
    public void getAllTest() {
        List<Genre> genresListFromDB = genreDao.getAll();
        assertEquals(genresListFromDB.size(), 2);
    }

    @Test
    public void updateTest() {
        genreDao.update(2, "Detektiv");
        Genre genreFromDB = genreDao.getById(2);
        assertEquals(genreFromDB.getName(), "Detektiv");
    }

    @Test
    public void deleteTest() {
        genreDao.deleteById(2);
        List<Genre> genresListFromDB = genreDao.getAll();
        assertEquals(genresListFromDB.size(), 1);

        List<Author> authorsListFromDB = authorDao.getAll();
        assertEquals(authorsListFromDB.size(), 3);
        List<Book> booksFromDB = bookDao.getAll();
        assertEquals(booksFromDB.size(), 2);
        List<Comment> commentsFromDB = commentDao.getAll();
        assertEquals(commentsFromDB.size(), 2);
    }
}
