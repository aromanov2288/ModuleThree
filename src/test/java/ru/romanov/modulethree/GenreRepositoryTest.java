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
public class GenreRepositoryTest {

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
        Genre genre = genreRepository.save(new Genre("Detektiv"));
        Genre genreFromDB = genreRepository.findById(genre.getId()).get();
        assertEquals(genreFromDB.getName(), "Detektiv");
    }

    @Test
    public void getByIdTest() {
        Genre genreFromDB = genreRepository.findById(1).get();
        assertEquals(genreFromDB.getName(), "Povest");
    }

    @Test
    public void getAllTest() {
        List<Genre> genresListFromDB = genreRepository.findAll();
        assertEquals(genresListFromDB.size(), 2);
    }

    @Test
    public void updateTest() {
        genreRepository.update(2, "Detektiv");
        Genre genreFromDB = genreRepository.findById(2).get();
        assertEquals(genreFromDB.getName(), "Detektiv");
    }

    @Test
    public void deleteTest() {
        genreRepository.deleteById(2);
        List<Genre> genresListFromDB = genreRepository.findAll();
        assertEquals(genresListFromDB.size(), 1);

        List<Author> authorsListFromDB = authorRepository.findAll();
        assertEquals(authorsListFromDB.size(), 3);
        List<Book> booksFromDB = bookRepository.findAll();
        assertEquals(booksFromDB.size(), 2);
        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(commentsFromDB.size(), 2);
    }
}
