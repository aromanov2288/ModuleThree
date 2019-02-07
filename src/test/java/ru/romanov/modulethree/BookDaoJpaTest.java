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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({AuthorDaoJpa.class, BookDaoJpa.class, CommentDaoJpa.class, GenreDaoJpa.class})
public class BookDaoJpaTest {

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
        Author author_1 = authorDao.getById(1);
        Author author_2 = authorDao.getById(2);
        Set<Author> authorsSet = new HashSet<>(Arrays.asList(author_1, author_2));
        Genre genre = genreDao.getById(1);
        int id = bookDao.insert(new Book("BookNewName", genre, authorsSet));
        Book bookFromDB = bookDao.getById(id);
        assertEquals(bookFromDB.getName(), "BookNewName");
        assertEquals(bookFromDB.getGenre(), genre);
        assertEquals(bookFromDB.getAuthorsSet(), authorsSet);
    }

    @Test
    public void getByIdTest() {
        Book bookFromDB = bookDao.getById(1);
        assertEquals(bookFromDB.getName(), "Book1Name");
        assertEquals(bookFromDB.getGenre().getName(), "Povest");
        assertEquals(bookFromDB.getAuthorsSet().size(), 2);
        Set<String> authorFiosSet = new HashSet<>();
        for(Author author : bookFromDB.getAuthorsSet()) {
            authorFiosSet.add(author.getFio());
        }
        assertTrue(authorFiosSet.contains("Pushkin"));
        assertTrue(authorFiosSet.contains("Lermontov"));
    }

    @Test
    public void getAllTest() {
        List<Book> booksListFromDB = bookDao.getAll();
        assertEquals(booksListFromDB.size(), 3);
    }

    @Test
    public void updateTest() {
        Author author_1 = authorDao.getById(1);
        Set<Author> authorsSet = new HashSet<>(Collections.singletonList(author_1));
        Genre genre = genreDao.getById(1);
        bookDao.update(2, "BookNewName", genre, authorsSet);
        Book bookFromDB = bookDao.getById(2);
        assertEquals(bookFromDB.getName(), "BookNewName");
        assertEquals(bookFromDB.getGenre(), genre);
        assertEquals(bookFromDB.getAuthorsSet(), authorsSet);
    }

    @Test
    public void deleteTest() {
        bookDao.deleteById(1);
        List<Book> booksListFromDB = bookDao.getAll();
        assertEquals(booksListFromDB.size(), 2);

        List<Author> authorsListFromDB = authorDao.getAll();
        assertEquals(authorsListFromDB.size(), 3);
        List<Genre> genresListFromDB = genreDao.getAll();
        assertEquals(genresListFromDB.size(), 2);
        List<Comment> commentsFromDB = commentDao.getAll();
        assertEquals(commentsFromDB.size(), 3);
    }
}
