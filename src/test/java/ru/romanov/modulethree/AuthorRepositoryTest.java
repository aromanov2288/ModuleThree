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
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
//@Import({AuthorDaoJpa.class, BookDaoJpa.class, CommentDaoJpa.class, GenreDaoJpa.class})
public class AuthorRepositoryTest {

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
        Author author = authorRepository.save(new Author("Dontsova"));
        Author authorFromDB = authorRepository.findById(author.getId()).get();
        assertEquals(authorFromDB.getFio(), "Dontsova");
    }

    @Test
    public void getByIdTest() {
        Author authorFromDB = authorRepository.findById(1).get();
        assertEquals(authorFromDB.getFio(), "Pushkin");
    }

    @Test
    public void getAllTest() {
        List<Author> authorsListFromDB = authorRepository.findAll();
        assertEquals(authorsListFromDB.size(), 3);
    }

    @Test
    public void updateTest() {
        authorRepository.update(2, "Dontsova");
        Author authorFromDB = authorRepository.findById(2).get();
        assertEquals(authorFromDB.getFio(), "Dontsova");
    }

    @Test
    public void deleteTest() {
        authorRepository.deleteById(3);
        List<Author> authorsListFromDB = authorRepository.findAll();
        assertEquals(authorsListFromDB.size(), 2);

        List<Book> booksFromDB = bookRepository.findAll();
        assertEquals(booksFromDB.size(), 2);
        List<Genre> genresListFromDB = genreRepository.findAll();
        assertEquals(genresListFromDB.size(), 2);
        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(commentsFromDB.size(), 3);
    }
}
