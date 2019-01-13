package ru.romanov.modulethree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.romanov.modulethree.dao.AuthorDao;
import ru.romanov.modulethree.dao.BookDao;
import ru.romanov.modulethree.dao.GenreDao;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Genre;

@ShellComponent
public class Commands {

    private AuthorDao authorDao;
    private GenreDao genreDao;
    private BookDao bookDao;


    @Autowired
    public Commands(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    @ShellMethod(value = "Get books count.", key = "books_count")
    public String booksCount() {
        return String.valueOf(bookDao.getCount());
    }

    @ShellMethod(value = "Insert new book.", key = "insert_book")
    public void insertBook(@ShellOption String bookName, @ShellOption int authorId, @ShellOption int genreId) {
        bookDao.insert(bookName, authorId, genreId);
    }

    @ShellMethod(value = "Get book by id.", key = "get_book")
    public String getBookById(@ShellOption int id){
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "Get all books.", key = "get_all_books")
    public void getAllBooks() {
        for(Book book : bookDao.getAll()) {
            System.out.println(book.toString());
        }
    }

    @ShellMethod(value = "Update book.", key = "update_book")
    public void updateBook(@ShellOption int id, @ShellOption String bookName, @ShellOption int authorId, @ShellOption int genreId) {
        bookDao.update(new Book(id, bookName, authorId, genreId));
    }

    @ShellMethod(value = "Delete book by id.", key = "delete_book")
    public void deleteBookById(@ShellOption int id) {
        bookDao.deleteByIb(id);
    }



    @ShellMethod(value = "Get authors count.", key = "authors_count")
    public String authorsCount() {
        return String.valueOf(authorDao.getCount());
    }

    @ShellMethod(value = "Insert new author.", key = "insert_author")
    public void insertAuthor(@ShellOption String name) {
        authorDao.insert(name);
    }

    @ShellMethod(value = "Get author by id.", key = "get_author")
    public String getAuthorById(@ShellOption int id){
        return authorDao.getById(id).toString();
    }

    @ShellMethod(value = "Get all authors.", key = "get_all_authors")
    public void getAllAuthors() {
        for(Author author : authorDao.getAll()) {
            System.out.println(author.toString());
        }
    }

    @ShellMethod(value = "Update author.", key = "update_author")
    public void updateAuthor(@ShellOption int id, @ShellOption String name) {
        authorDao.update(new Author(id, name));
    }

    @ShellMethod(value = "Delete author by id.", key = "delete_author")
    public void deleteAuthorById(@ShellOption int id) {
        authorDao.deleteByIb(id);
    }



    @ShellMethod(value = "Get genres count.", key = "genres_count")
    public String genresCount() {
        return String.valueOf(genreDao.getCount());
    }

    @ShellMethod(value = "Insert new author", key = "insert_genre")
    public void insertGenre(@ShellOption String name) {
        genreDao.insert(name);
    }

    @ShellMethod(value = "Get genre by id.", key = "get_genre")
    public String getGenreById(@ShellOption int id){
        return genreDao.getById(id).toString();
    }

    @ShellMethod(value = "Get all genres.", key = "get_all_genres")
    public void getAllGenres() {
        for(Genre genre : genreDao.getAll()) {
            System.out.println(genre.toString());
        }
    }

    @ShellMethod(value = "Update genre.", key = "update_genre")
    public void updateGenre(@ShellOption int id, @ShellOption String name) {
        genreDao.update(new Genre(id, name));
    }

    @ShellMethod(value = "Delete genre by id.", key = "delete_genre")
    public void deleteGenreById(@ShellOption int id) {
        genreDao.deleteByIb(id);
    }
}
