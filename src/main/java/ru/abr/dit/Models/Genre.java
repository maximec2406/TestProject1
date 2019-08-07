package ru.abr.dit.Models;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

    @ManyToMany
    @JoinTable (name="TP1_BOOK_GENRE",
            joinColumns=@JoinColumn (name="genre_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Genre> books;

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Genre> getBooks() {
        return books;
    }

    public void setBooks(List<Genre> books) {
        this.books = books;
    }
}
