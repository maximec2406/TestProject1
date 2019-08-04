package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "TP1_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @Column (nullable = false, length = 500)
    private String name;

    public Role() {
        this.create_time = new Date();
    }

    public Role(String name) {
        this.name = name;
        this.create_time = new Date();
    }
}
