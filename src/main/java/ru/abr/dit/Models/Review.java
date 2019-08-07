package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "TP1_REVIEW")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_change_date;

    @Column
    private String text;

    // сделай enumirated
    @ManyToOne (targetEntity = ru.abr.dit.Models.Visibility.class)
    private Visibility visability;

    public Review() {
        this.create_time = new Date();
    }

    public Review(Book book, User user) {
        this.book = book;
        this.user = user;
        this.create_time = new Date();

    }

    public int getId() {
        return id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLast_change_date() {
        return last_change_date;
    }

    public void setLast_change_date(Date last_change_date) {
        this.last_change_date = last_change_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Visibility getVisability() {
        return visability;
    }

    public void setVisability(Visibility visability) {
        this.visability = visability;
    }
}
