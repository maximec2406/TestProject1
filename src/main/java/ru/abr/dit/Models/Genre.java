package ru.abr.dit.Models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    @Column
    private String discription;

//    @Column (nullable = false, unique = true)
//    private int code;

    @ManyToMany
    @JoinTable (name="TP1_BOOK_GENRE",
            joinColumns=@JoinColumn (name="genre_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Book> books;

    public Genre() {
    }

    public Genre(String name, String discription) {
        this.name = name;
        this.discription = discription;
        this.create_time = new Date();
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

//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
