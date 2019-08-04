package ru.abr.dit.Models;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table (name = "TP1_GENRE")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (nullable = false, unique = true)
    private int code;
}
