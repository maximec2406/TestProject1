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

    @ManyToOne
    private User user_from;

    @ManyToOne
    private User user_to;

    @Column
    private String bookid;

    public Recomendations() {
        this.create_time = new Date();
    }

    public Recomendations(User user_from, User user_to, String bookid) {
        this.user_from = user_from;
        this.user_to = user_to;
        this.bookid = bookid;
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

    public String getBook() {
        return bookid;
    }

    public void setBook(String bookid) {
        this.bookid = bookid;
    }
}
