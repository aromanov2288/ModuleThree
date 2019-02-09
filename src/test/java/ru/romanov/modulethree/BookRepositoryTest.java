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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
//@Import({AuthorDaoJpa.class, BookDaoJpa.class, CommentDaoJpa.class, GenreDaoJpa.class})
public class BookRepositoryTest {

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
        Author author_1 = authorRepository.findById(1).get();
        Author author_2 = authorRepository.findById(2).get();
        Set<Author> authorsSet = new HashSet<>(Arrays.asList(author_1, author_2));
        Genre genre = genreRepository.findById(1).get();
        Book book = bookRepository.save(new Book("BookNewName", genre, authorsSet));
        Book bookFromDB = bookRepository.findById(book.getId()).get();
        assertEquals(bookFromDB.getName(), "BookNewName");
        assertEquals(bookFromDB.getGenre(), genre);
        assertEquals(bookFromDB.getAuthorsSet(), authorsSet);
    }

    @Test
    public void getByIdTest() {
        Book bookFromDB = bookRepository.findById(1).get();
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
        List<Book> booksListFromDB = bookRepository.findAll();
        assertEquals(booksListFromDB.size(), 3);
    }

    @Test
    public void updateTest() {
        Author author_1 = authorRepository.findById(1).get();
        Set<Author> authorsSet = new HashSet<>(Collections.singletonList(author_1));
        Genre genre = genreRepository.findById(1).get();
        Book book = new Book("BookNewName", genre, authorsSet);
        book.setId(2);
        bookRepository.save(book);
        Book bookFromDB = bookRepository.findById(2).get();
        assertEquals(bookFromDB.getName(), "BookNewName");
        assertEquals(bookFromDB.getGenre(), genre);
        assertEquals(bookFromDB.getAuthorsSet(), authorsSet);
    }

    @Test
    public void deleteTest() {
        bookRepository.deleteById(1);
        List<Book> booksListFromDB = bookRepository.findAll();
        assertEquals(booksListFromDB.size(), 2);

        List<Author> authorsListFromDB = authorRepository.findAll();
        assertEquals(authorsListFromDB.size(), 3);
        List<Genre> genresListFromDB = genreRepository.findAll();
        assertEquals(genresListFromDB.size(), 2);
        List<Comment> commentsFromDB = commentRepository.findAll();
        assertEquals(commentsFromDB.size(), 3);
    }
}
