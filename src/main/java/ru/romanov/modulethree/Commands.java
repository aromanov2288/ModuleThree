package ru.romanov.modulethree;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.romanov.modulethree.dao.AuthorRepository;
import ru.romanov.modulethree.dao.BookRepository;
import ru.romanov.modulethree.dao.CommentRepository;
import ru.romanov.modulethree.dao.GenreRepository;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Comment;
import ru.romanov.modulethree.domain.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ShellComponent
public class Commands {

    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;
    private BookRepository bookRepository;
    private CommentRepository commentRepository;

    public Commands(AuthorRepository authorRepository, GenreRepository genreRepository,
                    BookRepository bookRepository, CommentRepository commentRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }


    @ShellMethod(value = "Insert new author.", key = "insert_author")
    public void insertAuthor(@ShellOption String fio) {
        authorRepository.save(new Author(fio));
    }

    @ShellMethod(value = "Get author by id", key = "get_author_by_id")
    public String getAuthorById(@ShellOption int id){
        Author author = authorRepository.findById(id).get();
        return author.toString();
    }

    @ShellMethod(value = "Get all authors", key = "get_all_authors")
    public String getAllAuthors(){
        List<Author> authorsList = authorRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (Author author : authorsList) {
            builder.append(author.toString()).append("\n");
        }
        return builder.toString();
    }

    @ShellMethod(value = "Update author.", key = "update_author")
    public void updateAuthor(@ShellOption int id, @ShellOption String fio) {
        authorRepository.update(id, fio);
    }

    @ShellMethod(value = "Delete author", key = "delete_author_by_id")
    public void deleteAuthorById(@ShellOption int id) {
        authorRepository.deleteById(id);
    }


    @ShellMethod(value = "Insert new book.", key = "insert_book")
    public void insertBook(@ShellOption String name, @ShellOption int genreId, @ShellOption(arity = 3) int[] authorIds) {
        Set<Author> authorsSet = new HashSet<>();
        for(int authorId : authorIds) {
            if (authorId != 0) {
                Author author = authorRepository.findById(authorId).get();
                authorsSet.add(author);
            }
        }
        Genre genre = genreRepository.findById(genreId).get();
        bookRepository.save(new Book(name, genre, authorsSet));
    }

    @ShellMethod(value = "Get book by id", key = "get_book_by_id")
    public String getBookById(@ShellOption int id){
        Book book = bookRepository.findById(id).get();
        return book.toString();
    }

    @ShellMethod(value = "Get all books", key = "get_all_books")
    public String getAllBooks(){
        List<Book> booksList = bookRepository.findAll();
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
                Author author = authorRepository.findById(authorId).get();
                authorsSet.add(author);
            }
        }
        Genre genre = genreRepository.findById(genreId).get();
        Book book = new Book(name, genre, authorsSet);
        book.setId(id);
        bookRepository.save(book);
    }

    @ShellMethod(value = "Delete book", key = "delete_book_by_id")
    public void deleteBookById(@ShellOption int id) {
        bookRepository.deleteById(id);
    }


    @ShellMethod(value = "Insert new comment", key = "insert_comment")
    public void insertComment(@ShellOption String text, @ShellOption int bookId) {
        Book book = bookRepository.findById(bookId).get();
        commentRepository.save(new Comment(text, book));
    }

    @ShellMethod(value = "Get comment by id", key = "get_comment_by_id")
    public String getCommentById(@ShellOption int id) {
        return commentRepository.findById(id).toString();
    }

    @ShellMethod(value = "Get all comments", key = "get_all_comments")
    public String getAllComments(){
        List<Comment> commentsList = commentRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (Comment comment : commentsList) {
            builder.append(comment.toString()).append("\n");
        }
        return builder.toString();
    }

    @ShellMethod(value = "Update comment", key = "update_comment")
    public void updateComment(@ShellOption int id, @ShellOption String text) {
        commentRepository.update(id, text);
    }

    @ShellMethod(value = "Delete comment", key = "delete_comment_by_id")
    public void deleteCommentById(@ShellOption int id) {
        commentRepository.deleteById(id);
    }


    @ShellMethod(value = "Insert new genre", key = "insert_genre")
    public void insertGenre(@ShellOption String name) {
        genreRepository.save(new Genre(name));
    }

    @ShellMethod(value = "Get genre by id", key = "get_genre_by_id")
    public String getGenreById(@ShellOption int id){
        return genreRepository.findById(id).get().toString();
    }

    @ShellMethod(value = "Get all genres", key = "get_all_genres")
    public String getAllGenres(){
        List<Genre> genresList = genreRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (Genre genre : genresList) {
            builder.append(genre.toString()).append("\n");
        }
        return builder.toString();
    }

    @ShellMethod(value = "Update genre.", key = "update_genre")
    public void updateGenre(@ShellOption int id, @ShellOption String name) {
        genreRepository.update(id, name);
    }

    @ShellMethod(value = "Delete genre", key = "delete_genre_by_id")
    public void deleteGenreById(@ShellOption int id) {
        genreRepository.deleteById(id);
    }
}
