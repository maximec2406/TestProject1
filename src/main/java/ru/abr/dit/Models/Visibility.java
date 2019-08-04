package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "TP1_VISIBILITY")
public class Visibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @Column (nullable = false)
    private String name;

    public Visibility(String name) {
        this.name = name;
        this.create_time = new Date();
    }

    public Visibility() {
        this.create_time = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
