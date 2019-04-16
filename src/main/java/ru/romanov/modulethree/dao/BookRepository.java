package ru.romanov.modulethree.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.romanov.modulethree.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByGenreLike(String genre);

    @Query("{'authors.fio': ?#{#fio}}")
    List<Book> findAllByAuthorFio(@Param("fio")String fio);

    @Query("{'comments.commentator': ?0}")
    List<Book> findAllByCommentCommentator(String commentator);

    void deleteAllByGenre(String genre);

    @Query(value = "{'authors.fio': ?0}", delete = true)
    void deleteAllByAuthorFio(String fio);
}
