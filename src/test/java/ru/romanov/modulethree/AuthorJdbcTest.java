package ru.romanov.modulethree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.romanov.modulethree.dao.AuthorDao;
import ru.romanov.modulethree.dao.AuthorJdbc;
import ru.romanov.modulethree.domain.Author;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(AuthorJdbc.class)
public class AuthorJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void getCountTest() {
        assertEquals(authorDao.getCount(), 5);
    }

    @Test
    public void insertTest() {
        authorDao.insert("Dontsova");
        assertEquals(authorDao.getCount(), 6);
    }

    @Test
    public void getByIdTest() {
        Author author = authorDao.getById(2);
        assertEquals(author.getAuthorName(), "Lermontov");
    }

    @Test
    public void getAllTest() {
        List<Author> authorList = authorDao.getAll();
        assertEquals(authorList.size(), 5);
    }

    @Test
    public void updateTest() {
        Author newAuthor = new Author(1, "Dontsova");
        authorDao.update(newAuthor);
        Author updatedAuthor = authorDao.getById(1);
        assertEquals(updatedAuthor.getAuthorName(), "Dontsova");
    }

    @Test
    public void deleteById() {
        authorDao.deleteByIb(5);
        assertEquals(authorDao.getCount(), 4);
    }
}
