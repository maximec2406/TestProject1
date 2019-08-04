package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "TP1_RECOMENDATIONS")
public class Recomendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @Column (nullable = false)
    @ManyToOne
    private User user_from;

    @Column (nullable = false)
    @ManyToOne
    private User user_to;

    @Column
    @ManyToOne
    private Book book;

    public Recomendations() {
        this.create_time = new Date();
    }

    public Recomendations(User user_from, User user_to, Book book) {
        this.user_from = user_from;
        this.user_to = user_to;
        this.book = book;
        this.create_time = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public User getUser_from() {
        return user_from;
    }

    public void setUser_from(User user_from) {
        this.user_from = user_from;
    }

    public User getUser_to() {
        return user_to;
    }

    public void setUser_to(User user_to) {
        this.user_to = user_to;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
