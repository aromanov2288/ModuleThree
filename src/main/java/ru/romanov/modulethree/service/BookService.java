package ru.romanov.modulethree.service;

public interface BookService {

    void insertBook(String name, String genre, String[] authorFio, String[] authorYear);

    void insertComment(String id, String text, String commentator);

    String getBookById(String id);

    String getAllBooks();

    String getAllGenres();

    String getAllAuthors();

    String getAllComments(String commentator);

    void updateBook(String id, String name, String genre, String[] authorFio, String[] authorYear);

    void updateGenre(String oldGenre, String newGenre);

    void updateAuthor(String oldFio, String newFio);

    void updateComment(String id, String oldText, String newText, String commentator);

    void deleteBookById(String id);

    void deleteGenre(String genre);

    void deleteAuthorByFio(String fio);

    void deleteComment(String id, String text, String commentator);
}
