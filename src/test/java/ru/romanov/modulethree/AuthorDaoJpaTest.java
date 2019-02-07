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
public class AuthorDaoJpaTest {

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
        int id = authorDao.insert(new Author("Dontsova"));
        Author authorFromDB = authorDao.getById(id);
        assertEquals(authorFromDB.getFio(), "Dontsova");
    }

    @Test
    public void getByIdTest() {
        Author authorFromDB = authorDao.getById(1);
        assertEquals(authorFromDB.getFio(), "Pushkin");
    }

    @Test
    public void getAllTest() {
        List<Author> authorsListFromDB = authorDao.getAll();
        assertEquals(authorsListFromDB.size(), 3);
    }

    @Test
    public void updateTest() {
        authorDao.update(2, "Dontsova");
        Author authorFromDB = authorDao.getById(2);
        assertEquals(authorFromDB.getFio(), "Dontsova");
    }

    @Test
    public void deleteTest() {
        authorDao.deleteById(3);
        List<Author> authorsListFromDB = authorDao.getAll();
        assertEquals(authorsListFromDB.size(), 2);

        List<Book> booksFromDB = bookDao.getAll();
        assertEquals(booksFromDB.size(), 2);
        List<Genre> genresListFromDB = genreDao.getAll();
        assertEquals(genresListFromDB.size(), 2);
        List<Comment> commentsFromDB = commentDao.getAll();
        assertEquals(commentsFromDB.size(), 3);
    }
}
