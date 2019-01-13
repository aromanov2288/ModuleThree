package ru.romanov.modulethree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.romanov.modulethree.dao.BookDao;
import ru.romanov.modulethree.dao.BookJdbc;
import ru.romanov.modulethree.domain.Book;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(BookJdbc.class)
public class BookJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void getCountTest() {
        assertEquals(bookDao.getCount(), 3);
    }

    @Test
    public void insertTest() {
        bookDao.insert("NewBook", 3, 3);
        assertEquals(bookDao.getCount(), 4);
    }

    @Test
    public void getByIdTest() {
        Book book = bookDao.getById(2);
        assertEquals(book.getBookName(), "Nos");
        assertEquals(book.getAuthorName(), "Lermontov");
        assertEquals(book.getGenreName(), "Stihotvorenie");
    }

    @Test
    public void getAllTest() {
        List<Book> bookList = bookDao.getAll();
        assertEquals(bookList.size(), 3);
    }

    @Test
    public void updateTest() {
        Book newBook = new Book(1, "WhatIsIt",3, 3);
        bookDao.update(newBook);
        Book updatedBook = bookDao.getById(1);
        assertEquals(updatedBook.getBookName(), "WhatIsIt");
        assertEquals(updatedBook.getAuthorName(), "Tolstoj");
        assertEquals(updatedBook.getGenreName(), "Skazka");
    }

    @Test
    public void deleteById() {
        bookDao.deleteByIb(3);
        assertEquals(bookDao.getCount(), 2);
    }
}

