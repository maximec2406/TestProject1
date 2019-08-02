package ru.abr.dit.Models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TP1_Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    private Date create_time;

    @Column (length = 500, nullable = false)
    private String first_name;

    @Column (length = 1000, nullable = false)
    private String last_name;

    @Column (length = 1000)
    private String patronymic;

    @Column (nullable = false)
    private Date birthday;

    @Column
    private Date deathday;

    @Column (length = 2000)
    private String about;

    @Column
    private String photo;

    @Column //сделай выбор из списка
    private String country;

    public Author() {
    }

    public Author(String first_name, String last_name, Date birthday) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
