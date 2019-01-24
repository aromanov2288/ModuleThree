package ru.romanov.modulethree.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authorsSet;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Comment> commentsSet;

    public Book() {
    }

    public Book(String name, Genre genre, Set<Author> authorsSet) {
        this.name = name;
        this.genre = genre;
        this.authorsSet = authorsSet;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
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
        StringBuilder builder = new StringBuilder("Book{");
        builder.append("id=").append(id);
        builder.append(", name=").append(name);
        builder.append(", genre=").append(genre.getName());
        if (authorsSet != null) {
            builder.append(", authorsSet={");
            if (!authorsSet.isEmpty()) {
                int i = 0;
                for(Author author : authorsSet) {
                    i++;
                    builder.append(author.getFio());
                    if (i != authorsSet.size()) {
                        builder.append(", ");
                    }
                }
            }
            builder.append("}");
        }
        if (commentsSet != null) {
            builder.append(", commentsList={");
            if (!commentsSet.isEmpty()) {
                int i = 0;
                for(Comment comment : commentsSet) {
                    i++;
                    builder.append(comment.getText());
                    if (i != commentsSet.size()) {
                        builder.append(", ");
                    }
                }
            } else {
                builder.append("Нет комментариев");
            }
            builder.append("}");
        }
        return builder.toString();
    }
}
