package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "TP1_FAVORITES")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
}
