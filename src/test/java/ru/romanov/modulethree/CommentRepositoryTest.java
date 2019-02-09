package ru.romanov.modulethree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.romanov.modulethree.dao.AuthorRepository;
import ru.romanov.modulethree.dao.BookRepository;
import ru.romanov.modulethree.dao.CommentRepository;
import ru.romanov.modulethree.dao.GenreRepository;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Comment;
import ru.romanov.modulethree.domain.Genre;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
//@Import({AuthorDaoJpa.class, BookDaoJpa.class, CommentDaoJpa.class, GenreDaoJpa.class})
public class CommentRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void insertTest() {
        Book book = bookRepository.findById(1).get();
        Comment comment = commentRepository.save(new Comment("Nravitsa!", book));
        Comment commentFromDB = commentRepository.findById(comment.getId()).get();
        assertEquals(commentFromDB.getText(), "Nravitsa!");
        assertEquals(commentFromDB.getBook(), book);
    }

    @Test
    public void getByIdTest() {
        Book book = bookRepository.findById(1).get();
        Comment comment = commentRepository.findById(1).get();
        assertEquals(comment.getText(), "Super");
        assertEquals(comment.getBook(), book);
    }

    @Test
    public void getAllTest() {
        List<Comment> genresListFromDB = commentRepository.findAll();
        assertEquals(genresListFromDB.size(), 4);
    }

    @Test
    public void updateTest() {
        commentRepository.update(2, "Ochen nravitsa!");
        Comment commentFromDB = commentRepository.findById(2).get();
        assertEquals(commentFromDB.getText(), "Ochen nravitsa!");
    }

    @Test
    public void deleteTest() {
        commentRepository.deleteById(2);
        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(commentsFromDB.size(), 3);

        List<Author> authorsListFromDB = authorRepository.findAll();
        assertEquals(authorsListFromDB.size(), 3);
        List<Book> booksFromDB = bookRepository.findAll();
        assertEquals(booksFromDB.size(), 3);
        List<Genre> genresListFromDB = genreRepository.findAll();
        assertEquals(genresListFromDB.size(), 2);
    }
}
