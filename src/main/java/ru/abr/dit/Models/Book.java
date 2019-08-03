package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    private Date create_time;

    @Column (nullable = false, length = 500)
    private String name;

    @Column (length = 3000)
    private String discription;

    @Column (length = 4)
    private String year;

    @Column (nullable = false)
    private float rating;

    @Column // сделай enum
    private String original_lang;

    @ManyToOne (fetch = FetchType.LAZY)
    private Author author;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }
}
