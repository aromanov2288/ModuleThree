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
public class CommentDaoJpaTest {

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
        Book book = bookDao.getById(1);
        int id = commentDao.insert(new Comment("Nravitsa!", book));
        Comment commentFromDB = commentDao.getById(id);
        assertEquals(commentFromDB.getText(), "Nravitsa!");
        assertEquals(commentFromDB.getBook(), book);
    }

    @Test
    public void getByIdTest() {
        Book book = bookDao.getById(1);
        Comment comment = commentDao.getById(1);
        assertEquals(comment.getText(), "Super");
        assertEquals(comment.getBook(), book);
    }

    @Test
    public void getAllTest() {
        List<Comment> genresListFromDB = commentDao.getAll();
        assertEquals(genresListFromDB.size(), 4);
    }

    @Test
    public void updateTest() {
        commentDao.update(2, "Ochen nravitsa!");
        Comment commentFromDB = commentDao.getById(2);
        assertEquals(commentFromDB.getText(), "Ochen nravitsa!");
    }

    @Test
    public void deleteTest() {
        commentDao.deleteById(2);
        List<Comment> commentsFromDB = commentDao.getAll();
        assertEquals(commentsFromDB.size(), 3);

        List<Author> authorsListFromDB = authorDao.getAll();
        assertEquals(authorsListFromDB.size(), 3);
        List<Book> booksFromDB = bookDao.getAll();
        assertEquals(booksFromDB.size(), 3);
        List<Genre> genresListFromDB = genreDao.getAll();
        assertEquals(genresListFromDB.size(), 2);
    }
}
