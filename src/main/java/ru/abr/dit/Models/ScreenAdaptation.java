package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TP1_SCREEN_ADAPTATION")
public class ScreenAdaptation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @ManyToOne
    private Book book;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String year;

    @Column
    private String poster;

    @Column
    private String kinopoisk;

    public ScreenAdaptation() {
        this.create_time = new Date();
    }

    public ScreenAdaptation(Book book, String name) {
        this.book = book;
        this.name = name;
        this.create_time = new Date();
    }
}
