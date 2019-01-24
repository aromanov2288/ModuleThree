package ru.romanov.modulethree.domain;

public class Author {

    private int id;
    private String authorName;

    public Author() {
    }

    public Author(int id, String authorName) {
        this.id = id;
        this.authorName = authorName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
