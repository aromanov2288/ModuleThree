package ru.romanov.modulethree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.romanov.modulethree.dao.BookRepository;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Comment;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
        mongoTemplate.dropCollection(Book.class);
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(Book.class);
    }

    @Test
    public void saveTest() {
        Book newBook = createBook("Book1", "Pushkin", "Povest", 1800, "Super", "Vasya");
        Book savedBook = bookRepository.save(newBook);
        Book foundBook = mongoTemplate.findById(savedBook.getId(), Book.class);
        assertEquals(newBook, foundBook);
    }

    @Test
    public void saveAllTest() {
        Book newBook = createBook("Book1", "Pushkin", "Povest", 1800, "Super", "Vasya");
        bookRepository.saveAll(Collections.singletonList(newBook));
        List<Book> foundBooksList = mongoTemplate.findAll(Book.class);
        assertNotNull(foundBooksList);
        assertEquals(1, foundBooksList.size());
        assertEquals(newBook, foundBooksList.get(0));
    }

    @Test
    public void findByIdTest() {
        Book newBook = createBook("Book1", "Pushkin", "Povest", 1800, "Super", "Vasya");
        Book savedBook = mongoTemplate.save(newBook);
        Book foundBook = bookRepository.findById(savedBook.getId()).get();
        assertEquals(newBook, foundBook);
    }

    @Test
    public void findAllTest() {
        Book newBook = createBook("Book1", "Pushkin", "Povest", 1800, "Super", "Vasya");
        mongoTemplate.save(newBook);
        List<Book> foundBooksList = (List<Book>) bookRepository.findAll();
        assertNotNull(foundBooksList);
        assertEquals(1, foundBooksList.size());
        assertEquals(newBook, foundBooksList.get(0));
    }

    @Test
    public void findAllByGenreLikeTest() {
        Book newBook1 = createBook("Book1", "Povest", "Pushkin", 1800, "Super", "Vasya");
        Book newBook2 = createBook("Book2", "Rasskaz", "Lermonrov", 1900, "Kruto", "Vasya");
        Book newBook3 = createBook("Book3", "Povest", "Gogol", 2000, "Nravitsa", "Petya");
        mongoTemplate.save(newBook1);
        mongoTemplate.save(newBook2);
        mongoTemplate.save(newBook3);
        List<Book> foundBooksList = bookRepository.findAllByGenreLike("Povest");
        assertNotNull(foundBooksList);
        assertEquals(2, foundBooksList.size());
    }

    @Test
    public void findAllByAuthorFioText() {
        Book newBook1 = createBook("Book1", "Povest", "Pushkin", 1800, "Super", "Vasya");
        Book newBook2 = createBook("Book2", "Rasskaz", "Pushkin", 1900, "Kruto", "Vasya");
        Book newBook3 = createBook("Book3", "Povest", "Pushkin", 2000, "Nravitsa", "Petya");
        mongoTemplate.save(newBook1);
        mongoTemplate.save(newBook2);
        mongoTemplate.save(newBook3);
        List<Book> foundBooksList = bookRepository.findAllByAuthorFio("Pushkin");
        assertNotNull(foundBooksList);
        assertEquals(3, foundBooksList.size());
    }

    @Test
    public void findAllByCommentCommentatorTest() {
        Book newBook1 = createBook("Book1", "Povest", "Pushkin", 1800, "Super", "Vasya");
        Book newBook2 = createBook("Book2", "Rasskaz", "Pushkin", 1900, "Kruto", "Vasya");
        Book newBook3 = createBook("Book3", "Povest", "Pushkin", 2000, "Nravitsa", "Petya");
        mongoTemplate.save(newBook1);
        mongoTemplate.save(newBook2);
        mongoTemplate.save(newBook3);
        List<Book> foundBooksList = bookRepository.findAllByCommentCommentator("Vasya");
        assertNotNull(foundBooksList);
        assertEquals(2, foundBooksList.size());
    }

    @Test
    public void deleteByIdTest() {
        Book newBook1 = createBook("Book1", "Povest", "Pushkin", 1800, "Super", "Vasya");
        Book newBook2 = createBook("Book2", "Rasskaz", "Pushkin", 1900, "Kruto", "Vasya");
        Book newBook3 = createBook("Book3", "Povest", "Pushkin", 2000, "Nravitsa", "Petya");
        mongoTemplate.save(newBook1);
        mongoTemplate.save(newBook2);
        mongoTemplate.save(newBook3);
        bookRepository.deleteById(newBook1.getId());
        List<Book> foundBooksList = mongoTemplate.findAll(Book.class);
        assertNotNull(foundBooksList);
        assertEquals(2, foundBooksList.size());
    }

    @Test
    public void deleteAllByGenreTest() {
        Book newBook1 = createBook("Book1", "Povest", "Lermontov", 1800, "Super", "Vasya");
        Book newBook2 = createBook("Book2", "Rasskaz", "Pushkin", 1900, "Kruto", "Vasya");
        Book newBook3 = createBook("Book3", "Povest", "Pushkin", 2000, "Nravitsa", "Petya");
        mongoTemplate.save(newBook1);
        mongoTemplate.save(newBook2);
        mongoTemplate.save(newBook3);
        bookRepository.deleteAllByGenre("Rasskaz");
        List<Book> foundBooksList = mongoTemplate.findAll(Book.class);
        assertNotNull(foundBooksList);
        assertEquals(2, foundBooksList.size());
    }

    @Test
    public void deleteAllByAuthorFioTest() {
        Book newBook1 = createBook("Book1", "Povest", "Lermontov", 1800, "Super", "Vasya");
        Book newBook2 = createBook("Book2", "Rasskaz", "Pushkin", 1900, "Kruto", "Vasya");
        Book newBook3 = createBook("Book3", "Povest", "Pushkin", 2000, "Nravitsa", "Petya");
        mongoTemplate.save(newBook1);
        mongoTemplate.save(newBook2);
        mongoTemplate.save(newBook3);
        bookRepository.deleteAllByAuthorFio("Pushkin");
        List<Book> foundBooksList = mongoTemplate.findAll(Book.class);
        assertNotNull(foundBooksList);
        assertEquals(1, foundBooksList.size());
    }

    private Book createBook(String name, String genre, String fio, Integer year,
                            String text, String commentator) {
        Set<Author> authorsSet = Collections.singleton(new Author(fio, year));
        Book book = new Book(name, genre, authorsSet);
        Set<Comment> commentsSet = Collections.singleton(new Comment(text, commentator));
        book.setCommentsSet(commentsSet);
        return book;
    }
}
