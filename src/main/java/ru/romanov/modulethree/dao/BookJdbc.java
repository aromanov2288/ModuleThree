package ru.romanov.modulethree.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.romanov.modulethree.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbcTemplate;

    @Autowired
    public BookJdbc(NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOKS", (HashMap) null, Integer.class);

    }

    @Override
    public void insert(String bookName, int authorId, int genreId) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("bookName", bookName);
        params.put("authorId", authorId);
        params.put("genreId", genreId);
        jdbcTemplate.update("INSERT INTO BOOKS (BOOK_NAME, AUTHOR_ID, GENRE_ID) " +
                "VALUES (:bookName, :authorId, :genreId)", params);
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbcTemplate.queryForObject("SELECT BOOKS.ID, BOOK_NAME, AUTHOR_NAME, GENRE_NAME FROM BOOKS " +
                        "INNER JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.ID " +
                        "INNER JOIN GENRES ON BOOKS.GENRE_ID = GENRES.ID WHERE BOOKS.ID = :id", params, new BookRowMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT BOOKS.ID, BOOK_NAME, AUTHOR_NAME, GENRE_NAME FROM BOOKS " +
                "INNER JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.ID " +
                "INNER JOIN GENRES ON BOOKS.GENRE_ID = GENRES.ID", new BookRowMapper());
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("id", book.getId());
        params.put("bookName", book.getBookName());
        params.put("authorId", book.getAuthorId());
        params.put("genreId", book.getGenreId());
        jdbcTemplate.update("UPDATE BOOKS SET BOOK_NAME = :bookName, AUTHOR_ID = :authorId, GENRE_ID = :genreId WHERE ID = :id", params);
    }

    @Override
    public void deleteByIb(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbcTemplate.update("DELETE FROM BOOKS WHERE ID = :id", params);
    }

    class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("ID");
            String bookName = resultSet.getString("BOOK_NAME");
            String authorName = resultSet.getString("AUTHOR_NAME");
            String genreName = resultSet.getString("GENRE_NAME");
            return new Book(id, bookName, authorName, genreName);
        }
    }
}
