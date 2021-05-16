package ru.yakunin.stackoverflowparser.entity;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String dateOfCreated;
    private int answers;

    public Detail(String author,
                  String dateOfCreated, int answers) {
        this.author = author;
        this.dateOfCreated = dateOfCreated;
        this.answers = answers;
    }


    public Detail() {
    }

    public int getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getDateOfCreated() {
        return this.dateOfCreated;
    }

    public int getAnswers() {
        return this.answers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDateOfCreated(String dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }


    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", dateOfCreated='" + dateOfCreated + '\'' +
                ", answers=" + answers +
                '}';
    }
}
