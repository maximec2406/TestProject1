package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table (name = "TP1_BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
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

    @ManyToOne
    private Author author;

    @OneToMany (mappedBy = "book")
    private ArrayList<Review> reviews;

    @ManyToMany
    @JoinTable (name="TP1_GENRE",
            joinColumns=@JoinColumn (name="id"),
            inverseJoinColumns=@JoinColumn(name="id"))
    private ArrayList<Genre> genres;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOriginal_lang() {
        return original_lang;
    }

    public void setOriginal_lang(String original_lang) {
        this.original_lang = original_lang;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
