package ru.romanov.modulethree.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;
import java.util.Set;

@Document(collection = "books")
public class Book {

    @Id
    private String id;

    @Field(value = "name")
    private String name;

    @Field(value = "genre")
    private String genre;

    @Field(value = "authors")
    private Set<Author> authorsSet;

    @Field(value = "comments")
    private Set<Comment> commentsSet;

    public Book() {
    }

    public Book(String name, String genre, Set<Author> authorsSet) {
        this.name = name;
        this.genre = genre;
        this.authorsSet = authorsSet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Author> getAuthorsSet() {
        return authorsSet;
    }

    public void setAuthorsSet(Set<Author> authorsSet) {
        this.authorsSet = authorsSet;
    }

    public Set<Comment> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comment> commentsSet) {
        this.commentsSet = commentsSet;
    }

    @Override
    public String toString() {
        String authorsSetText = "{Авторы не найдены}";
        if (authorsSet != null && !authorsSet.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            for(Author author : authorsSet) {
                builder.append(author.toString());
            }
            builder.append("}");
            authorsSetText = builder.toString();
        }

        String commentsSetText = "{Комментарии не найдены}";
        if (commentsSet != null && !commentsSet.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            for(Comment comment : commentsSet) {
                builder.append(comment.toString());
            }
            builder.append("}");
            commentsSetText = builder.toString();
        }
        return "{id=" + id + ", name=" + name + ", genre=" + genre +
                ", authorsSet=" + authorsSetText + ", commentsSet=" + commentsSetText + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(authorsSet, book.authorsSet) &&
                Objects.equals(commentsSet, book.commentsSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, authorsSet, commentsSet);
    }
}
