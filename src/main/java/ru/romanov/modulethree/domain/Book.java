package ru.romanov.modulethree.domain;

public class Book {

    private int id;
    private String bookName;
    private Author author = new Author();
    private Genre genre = new Genre();

    public Book(int id, String bookName, int authorId, int genreId) {
        this.id = id;
        this.bookName = bookName;
        this.author.setId(authorId);
        this.genre.setId(genreId);
    }

    public Book(int id, String bookName, String authorName, String genreName) {
        this.id = id;
        this.bookName = bookName;
        this.author.setAuthorName(authorName);
        this.genre.setGenreName(genreName);
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getAuthorId() {
        return author.getId();
    }

    public String getAuthorName() {
        return author.getAuthorName();
    }

    public int getGenreId() {
        return genre.getId();
    }

    public String getGenreName() {
        return genre.getGenreName();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + author.getAuthorName() + '\'' +
                ", genreName='" + genre.getGenreName() + '\'' +
                '}';
    }
}
