package ru.romanov.modulethree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.romanov.modulethree.dao.AuthorDao;
import ru.romanov.modulethree.dao.BookDao;
import ru.romanov.modulethree.dao.CommentDao;
import ru.romanov.modulethree.dao.GenreDao;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Comment;
import ru.romanov.modulethree.domain.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ShellComponent
public class Commands {

    private AuthorDao authorDao;
    private GenreDao genreDao;
    private BookDao bookDao;
    private CommentDao commentDao;

    public Commands(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao, CommentDao commentDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
        this.commentDao = commentDao;
    }


    @ShellMethod(value = "Insert new author.", key = "insert_author")
    public void insertAuthor(@ShellOption String fio) {
        authorDao.insert(new Author(fio));
    }

    @ShellMethod(value = "Get author by id", key = "get_author_by_id")
    public String getAuthorById(@ShellOption int id){
        Author author = authorDao.getById(id);
        return author.toString();
    }

    @ShellMethod(value = "Get all authors", key = "get_all_authors")
    public String getAllAuthors(){
        List<Author> authorsList = authorDao.getAll();
        StringBuilder builder = new StringBuilder();
        for (Author author : authorsList) {
            builder.append(author.toString()).append("\n");
        }
        return builder.toString();
    }

    @ShellMethod(value = "Update author.", key = "update_author")
    public void updateAuthor(@ShellOption int id, @ShellOption String fio) {
        authorDao.update(id, fio);
    }

    @ShellMethod(value = "Delete author", key = "delete_author_by_id")
    public void deleteAuthorById(@ShellOption int id) {
        authorDao.deleteById(id);
    }


    @ShellMethod(value = "Insert new book.", key = "insert_book")
    public void insertBook(@ShellOption String name, @ShellOption int genreId, @ShellOption(arity = 3) int[] authorIds) {
        Set<Author> authorsSet = new HashSet<>();
        for(int authorId : authorIds) {
            if (authorId != 0) {
                Author author = authorDao.getById(authorId);
                authorsSet.add(author);
            }
        }
        Genre genre = genreDao.getById(genreId);
        bookDao.insert(new Book(name, genre, authorsSet));
    }

    @ShellMethod(value = "Get book by id", key = "get_book_by_id")
    public String getBookById(@ShellOption int id){
        Book book = bookDao.getById(id);
        return book.toString();
    }

    @ShellMethod(value = "Get all books", key = "get_all_books")
    public String getAllBooks(){
        List<Book> booksList = bookDao.getAll();
        StringBuilder builder = new StringBuilder();
        for (Book book : booksList) {
            builder.append(book.toString()).append("\n");
        }
        return builder.toString();
    }

    @ShellMethod(value = "Update book.", key = "update_book")
    public void updateBook(@ShellOption int id, @ShellOption String name, @ShellOption int genreId, @ShellOption(arity = 3) int[] authorIds) {
        Set<Author> authorsSet = new HashSet<>();
        for(int authorId : authorIds) {
            if (authorId != 0) {
                Author author = authorDao.getById(authorId);
                authorsSet.add(author);
            }
        }
        Genre genre = genreDao.getById(genreId);
        bookDao.update(id, name, genre, authorsSet);
    }

    @ShellMethod(value = "Delete book", key = "delete_book_by_id")
    public void deleteBookById(@ShellOption int id) {
        bookDao.deleteById(id);
    }


    @ShellMethod(value = "Insert new comment", key = "insert_comment")
    public void insertComment(@ShellOption String text, @ShellOption int bookId) {
        Book book = bookDao.getById(bookId);
        commentDao.insert(new Comment(text, book));
    }

    @ShellMethod(value = "Get comment by id", key = "get_comment_by_id")
    public String getCommentById(@ShellOption int id) {
        return commentDao.getById(id).toString();
    }

    @ShellMethod(value = "Get all comments", key = "get_all_comments")
    public String getAllComments(){
        List<Comment> commentsList = commentDao.getAll();
        StringBuilder builder = new StringBuilder();
        for (Comment comment : commentsList) {
            builder.append(comment.toString()).append("\n");
        }
        return builder.toString();
    }

    @ShellMethod(value = "Update comment", key = "update_comment")
    public void updateComment(@ShellOption int id, @ShellOption String text) {
        commentDao.update(id, text);
    }

    @ShellMethod(value = "Delete comment", key = "delete_comment_by_id")
    public void deleteCommentById(@ShellOption int id) {
        commentDao.deleteById(id);
    }


    @ShellMethod(value = "Insert new genre", key = "insert_genre")
    public void insertGenre(@ShellOption String name) {
        genreDao.insert(new Genre(name));
    }

    @ShellMethod(value = "Get genre by id", key = "get_genre_by_id")
    public String getGenreById(@ShellOption int id){
        return genreDao.getById(id).toString();
    }

    @ShellMethod(value = "Get all genres", key = "get_all_genres")
    public String getAllGenres(){
        List<Genre> genresList = genreDao.getAll();
        StringBuilder builder = new StringBuilder();
        for (Genre genre : genresList) {
            builder.append(genre.toString()).append("\n");
        }
        return builder.toString();
    }

    @ShellMethod(value = "Update genre.", key = "update_genre")
    public void updateGenre(@ShellOption int id, @ShellOption String name) {
        genreDao.update(id, name);
    }

    @ShellMethod(value = "Delete genre", key = "delete_genre_by_id")
    public void deleteGenreById(@ShellOption int id) {
        genreDao.deleteById(id);
    }
}
