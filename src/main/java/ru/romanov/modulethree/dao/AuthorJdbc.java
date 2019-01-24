package ru.romanov.modulethree.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.romanov.modulethree.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorJdbc implements AuthorDao {

    private NamedParameterJdbcOperations jdbcTemplate;

    @Autowired
    public AuthorJdbc(NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM AUTHORS", (HashMap) null, Integer.class);
    }

    @Override
    public void insert(String name) {
        Map<String, String> params = new HashMap<>(1);
        params.put("name", name);
        jdbcTemplate.update("INSERT INTO AUTHORS (AUTHOR_NAME) VALUES (:name)", params);
    }

    @Override
    public Author getById(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbcTemplate.queryForObject("SELECT * FROM AUTHORS WHERE ID = :id", params, new AuthorRowMapper());

    }

    @Override
    public List<Author> getAll() {
        return jdbcTemplate.query("SELECT * FROM AUTHORS",new AuthorRowMapper());
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("name", author.getAuthorName());
        params.put("id", author.getId());
        jdbcTemplate.update("UPDATE AUTHORS SET AUTHOR_NAME = :name WHERE ID= :id", params);
    }

    @Override
    public void deleteByIb(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbcTemplate.update("DELETE FROM AUTHORS WHERE ID = :id", params);
    }

    class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("AUTHOR_NAME");
            return new Author(id, name);
        }
    }
}
