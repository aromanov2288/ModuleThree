package ru.romanov.modulethree.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment() {
    }

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Comment{");
        builder.append("id=").append(id);
        builder.append(", text=").append(text);
        builder.append(", book=").append(book.getName());
        builder.append("}");
        return builder.toString();
    }
}
