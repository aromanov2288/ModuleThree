package ru.romanov.modulethree.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.romanov.modulethree.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreJdbc implements GenreDao {

    private NamedParameterJdbcOperations jdbcTemplate;

    @Autowired
    public GenreJdbc(NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM GENRES", (HashMap) null, Integer.class);
    }

    @Override
    public void insert(String name) {
        Map<String, String> params = new HashMap<>(1);
        params.put("name", name);
        jdbcTemplate.update("INSERT INTO GENRES (GENRE_NAME) VALUES (:name)", params);
    }

    @Override
    public Genre getById(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbcTemplate.queryForObject("SELECT * FROM GENRES WHERE ID = :id", params, new GenreRowMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query("SELECT * FROM GENRES", new GenreRowMapper());
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("name", genre.getGenreName());
        params.put("id", genre.getId());
        jdbcTemplate.update("UPDATE GENRES SET GENRE_NAME = :name WHERE ID = :id", params);
    }

    @Override
    public void deleteByIb(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbcTemplate.update("DELETE FROM GENRES WHERE ID = :id", params);
    }

    class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("GENRE_NAME");
            return new Genre(id, name);
        }
    }
}
