package ru.romanov.modulethree;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.romanov.modulethree.dao.BookRepository;
import ru.romanov.modulethree.service.BookService;

@ShellComponent
public class Commands {

    private BookService bookService;

    public Commands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Get all authors", key = "get_all_authors")
    public String getAllAuthors(){
        return bookService.getAllAuthors();
    }

    @ShellMethod(value = "Update author", key = "update_author")
    public void updateAuthor(@ShellOption String oldFio, @ShellOption String newFio) {
        bookService.updateAuthor(oldFio, newFio);
    }

    @ShellMethod(value = "Delete books by author fio", key = "delete_author")
    public void deleteAuthorByFio(@ShellOption String fio) {
        bookService.deleteAuthorByFio(fio);
    }

    @ShellMethod(value = "Insert new book", key = "insert_book")
    public void insertBook(@ShellOption String name, @ShellOption String genre,
                           @ShellOption(arity = 3) String[] authorFio, @ShellOption(arity = 3) String[] authorYear) {
        bookService.insertBook(name, genre, authorFio, authorYear);
    }

    @ShellMethod(value = "Get book by id", key = "get_book_by_id")
    public String getBookById(@ShellOption String id){
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "Get all books", key = "get_all_books")
    public String getAllBooks(){
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Update book.", key = "update_book")
    public void updateBook(@ShellOption String id, @ShellOption String name, @ShellOption String genre,
                           @ShellOption(arity = 3) String[] authorFio, @ShellOption(arity = 3) String[] authorYear) {
        bookService.updateBook(id, name, genre, authorFio, authorYear);
    }

    @ShellMethod(value = "Delete book by id", key = "delete_book_by_id")
    public void deleteBookById(@ShellOption String id) {
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "Insert new comment", key = "insert_comment")
    public void insertComment(@ShellOption String id, @ShellOption String text, @ShellOption String commentator) {
        bookService.insertComment(id, text, commentator);
    }

    @ShellMethod(value = "Get all comments", key = "get_all_comments")
    public String getAllComments(@ShellOption String commentator){
        return bookService.getAllComments(commentator);
    }

    @ShellMethod(value = "Update comment", key = "update_comment")
    public void updateComment(@ShellOption String id, @ShellOption String oldText,
                              @ShellOption String newText, @ShellOption String commentator) {
        bookService.updateComment(id, oldText, newText, commentator);
    }

    @ShellMethod(value = "Delete comment", key = "delete_comment")
    public void deleteCommentById(@ShellOption String id, @ShellOption String text, @ShellOption String commentator) {
        bookService.deleteComment(id, text, commentator);
    }

    @ShellMethod(value = "Get all genres", key = "get_all_genres")
    public String getAllGenres() {
        return bookService.getAllGenres();
    }

    @ShellMethod(value = "Update genre.", key = "update_genre")
    public void updateGenre(@ShellOption String oldGenre, @ShellOption String newGenre) {
        bookService.updateGenre(oldGenre, newGenre);
    }

    @ShellMethod(value = "Delete genre", key = "delete_genre")
    public void deleteGenreById(@ShellOption String genre) {
        bookService.deleteGenre(genre);
    }
}
