package ru.romanov.modulethree.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public class Author {

    @Field(value = "fio")
    private String fio;

    @Field(value = "year")
    private Integer year;

    public Author() {
    }

    public Author(String fio, Integer year) {
        this.fio = fio;
        this.year = year;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "{fio=" + fio + ", year=" + year + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(fio, author.fio) &&
                Objects.equals(year, author.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, year);
    }
}
