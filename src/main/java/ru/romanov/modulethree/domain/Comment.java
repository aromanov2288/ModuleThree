package ru.romanov.modulethree.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public class Comment {

    @Field(value = "text")
    private String text;

    @Field(value = "commentator")
    private String commentator;

    public Comment() {
    }

    public Comment(String text, String commentator) {
        this.text = text;
        this.commentator = commentator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    @Override
    public String toString() {
        return "{text=" + text + ", commentator=" + commentator + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text) &&
                Objects.equals(commentator, comment.commentator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, commentator);
    }
}
