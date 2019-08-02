package ru.abr.dit.Models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TP1_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    private Date create_time;

    @Column(unique = true, nullable = false)
    private String email;

    @Column (nullable = false)
    private String password_hash;

    @Column (unique = true, nullable = false)
    private String nickname;

    @Column (length = 500)
    private String first_name;

    @Column (length = 1000)
    private String last_name;

    @Column
    private Date birthday;

    @Column (length = 2000)
    private String about;

    @Column
    private String photo;

    @Column (nullable = false)
    private boolean locked;

    public User() {
    }

    public User(String email, String password_hash, String nickname) {
        this.email = email;
        this.password_hash = password_hash;
        this.nickname = nickname;
        this.locked = false;
        this.create_time = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
