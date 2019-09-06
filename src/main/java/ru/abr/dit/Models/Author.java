package ru.abr.dit.Models;
import ru.abr.dit.Enums.EnumCountry;
import ru.abr.dit.Enums.EnumCountryConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TP1_AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

//    Проверки для форм можно вешать и на сюда, на сущность, но верно ли это?
//    @NotNull(message = "Enter first name")
//    @Size(min = 5, max = 500, message = "Enter first name")
    @Column (length = 500, nullable = false)
    private String first_name;

//      Проверки для форм можно вешать и на сюда, на сущность, но верно ли это?
//    @NotNull(message = "Enter last name")
//    @Size(min = 5, max = 500, message = "Enter last name")
    @Column (length = 1000, nullable = false)
    private String last_name;

    @Column (length = 1000)
    private String patronymic;

    @Column (nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column
    @Temporal(TemporalType.DATE)
    private Date deathday;

    @Column (length = 2000)
    private String about;

    @Column
    private String photo;

    @Column //сделай выбор из списка
    @Convert(converter = EnumCountryConverter.class)
    private EnumCountry country;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

    public Author() {
    }

    public Author(String first_name, String last_name, Date birthday) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.create_time = new Date();
    }

    public Author(String first_name, String last_name, String patronymic, Date birthday, Date deathday, String about, String photo, EnumCountry country) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.deathday = deathday;
        this.about = about;
        this.photo = photo;
        this.country = country;
        this.create_time = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDeathday() {
        return deathday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public EnumCountry getCountry() {
        return country;
    }

    public void setCountry(EnumCountry country) {
        this.country = country;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFullName(){return this.last_name + " " + this.getFirst_name() + " " + this.getPatronymic(); }
}
