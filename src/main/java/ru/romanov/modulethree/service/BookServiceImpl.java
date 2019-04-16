package ru.romanov.modulethree.service;

import org.springframework.stereotype.Service;
import ru.romanov.modulethree.dao.BookRepository;
import ru.romanov.modulethree.domain.Author;
import ru.romanov.modulethree.domain.Book;
import ru.romanov.modulethree.domain.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void insertBook(String name, String genre, String[] authorFio, String[] authorYear) {
        Book book = new Book(name, genre, createAuthorsSet(authorFio, authorYear));
        bookRepository.save(book);
    }

    @Override
    public void insertComment(String id, String text, String commentator) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Comment comment = new Comment(text, commentator);
            if(book.getCommentsSet() == null) {
                Set<Comment> commentsSet = new HashSet<>();
                book.setCommentsSet(commentsSet);
            }
            book.getCommentsSet().add(comment);
            bookRepository.save(book);
        }
    }

    @Override
    public String getBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? book.get().toString() : "Книга с id " + id + " не найдена";
    }

    @Override
    public String getAllBooks() {
        List<Book> booksList = (List<Book>) bookRepository.findAll();
        if(booksList == null || booksList.isEmpty()) {
            return "Ни одной книги не найдено";
        }
        StringBuilder builder = new StringBuilder();
        for (Book book : booksList) {
            builder.append(book.toString()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String getAllGenres() {
        List<Book> booksList = (List<Book>) bookRepository.findAll();
        if(booksList == null || booksList.isEmpty()) {
            return "Ни одного жанра не найдено";
        }
        Set<String> genresSet = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (Book book : booksList) {
            if (!genresSet.contains(book.getGenre())) {
                genresSet.add(book.getGenre());
                builder.append(book.getGenre()).append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String getAllAuthors() {
        List<Book> booksList = (List<Book>) bookRepository.findAll();
        if(booksList == null || booksList.isEmpty()) {
            return "Ни одного автора не найдено";
        }
        Set<Author> authorsSet = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (Book book : booksList) {
            for (Author author : book.getAuthorsSet()) {
                if (!authorsSet.contains(author)) {
                    authorsSet.add(author);
                    builder.append(author.toString()).append("\n");
                }
            }
        }
        return builder.toString();
    }

    @Override
    public String getAllComments(String commentator) {
        List<Book> booksList = (List<Book>) bookRepository.findAllByCommentCommentator(commentator);
        if(booksList == null || booksList.isEmpty()) {
            return "Ни одного комментария не найдено";
        }
        Set<String> bookIdsSet = new HashSet<>();
        Map<String, Set<String>> commentsMap = new HashMap<>();
        for (Book book : booksList) {
            for (Comment comment : book.getCommentsSet()) {
                if(comment.getCommentator().equals(commentator)) {
                    if(!commentsMap.containsKey(book.getId())) {
                        Set<String> textsSet = new HashSet<>();
                        textsSet.add(comment.getText());
                        commentsMap.put(book.getId(), textsSet);
                        bookIdsSet.add(book.getId());
                    }
                    commentsMap.get(book.getId()).add(comment.getText());
                }
            }
        }
        if(bookIdsSet.isEmpty()) {
            return "Ни одного комментария не найдено";
        }
        StringBuilder builder = new StringBuilder();
        for (String id : bookIdsSet) {
            builder.append("{BookId:").append(id).append(": comments:{");
            for (String text : commentsMap.get(id)) {
                builder.append("-").append(text).append("-");
            }
            builder.append("}").append("\n");
        }
        return builder.toString();
    }

    @Override
    public void updateBook(String id, String name, String genre, String[] authorFio, String[] authorYear) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(name);
            book.setGenre(genre);
            book.setAuthorsSet(createAuthorsSet(authorFio, authorYear));
            bookRepository.save(book);
        }
    }

    @Override
    public void updateGenre(String oldGenre, String newGenre) {
        List<Book> booksList = bookRepository.findAllByGenreLike(oldGenre);
        if (booksList != null && !booksList.isEmpty()) {
            for (Book book : booksList) {
                book.setGenre(newGenre);
            }
            bookRepository.saveAll(booksList);
        }
    }

    @Override
    public void updateAuthor(String oldFio, String newFio) {
        List<Book> booksList = bookRepository.findAllByAuthorFio(oldFio);
        if(booksList != null && !booksList.isEmpty()) {
            for (Book book : booksList) {
                for (Author author : book.getAuthorsSet()) {
                    if(author.getFio().equals(oldFio)) {
                        author.setFio(newFio);
                        break;
                    }
                }
            }
            bookRepository.saveAll(booksList);
        }
    }

    @Override
    public void updateComment(String id, String oldText, String newText, String commentator) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            for(Comment comment : book.getCommentsSet()) {
                if (comment.getText().equals(oldText) && comment.getCommentator().equals(commentator)) {
                    comment.setText(newText);
                }
            }
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteGenre(String genre) {
        bookRepository.deleteAllByGenre(genre);
    }

    @Override
    public void deleteAuthorByFio(String fio) {
        bookRepository.deleteAllByAuthorFio(fio);
    }

    @Override
    public void deleteComment(String id, String text, String commentator) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            List<Comment> commentsList = new ArrayList<>();
            for(Comment comment : book.getCommentsSet()) {
                if (comment.getText().equals(text) && comment.getCommentator().equals(commentator)) {
                    commentsList.add(comment);
                }
            }
            book.getCommentsSet().removeAll(commentsList);
            bookRepository.save(book);
        }
    }

    private Set<Author> createAuthorsSet(String[] authorFio, String[] authorYear) {
        Set authorsSet = new HashSet();
        for(int i = 0; i < 3; i++) {
            if(authorFio[i].equals("*") || authorYear[i].equals("*")) {
                continue;
            }
            authorsSet.add(new Author(authorFio[i], Integer.parseInt(authorYear[i])));
        }
        return authorsSet;
    }
}
