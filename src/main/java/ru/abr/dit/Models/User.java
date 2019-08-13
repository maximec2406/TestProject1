package ru.abr.dit.Models;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TP1_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @Column(unique = true, nullable = false)
    private String email;

    @Column (unique = true, nullable = false)
    private String nickname;

    @Column (length = 500)
    private String first_name;

    @Column (length = 1000)
    private String last_name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column (length = 2000)
    private String about;

    @Column
    private String photo;

    @Column (nullable = false)
    private boolean locked;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany (mappedBy = "user_from")
    private List<Recomendations> recomendationsFrom;

    @OneToMany (mappedBy = "user_to")
    private List<Recomendations> recomendationsTo;

    @ManyToMany
    @JoinTable (name="TP1_USER_ROLE",
            joinColumns=@JoinColumn (name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private List<Role> roles;

    @OneToMany (mappedBy = "user_from")
    private List<Recomendations> rec_from_user;


    @OneToMany (mappedBy = "user_to")
    private List<Recomendations> rec_to_user;

    @Column
    private String encryptedPassword;

    public User() {
    }

    public User(String email, String encryptedPassword, String nickname) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Recomendations> getRec_from_user() {
        return rec_from_user;
    }

    public void setRec_from_user(List<Recomendations> rec_from_user) {
        this.rec_from_user = rec_from_user;
    }

    public List<Recomendations> getRec_to_user() {
        return rec_to_user;
    }

    public void setRec_to_user(List<Recomendations> rec_to_user) {
        this.rec_to_user = rec_to_user;
    }

    public List<Recomendations> getRecomendationsFrom() {
        return recomendationsFrom;
    }

    public void setRecomendationsFrom(List<Recomendations> recomendationsFrom) {
        this.recomendationsFrom = recomendationsFrom;
    }

    public List<Recomendations> getRecomendationsTo() {
        return recomendationsTo;
    }

    public void setRecomendationsTo(List<Recomendations> recomendationsTo) {
        this.recomendationsTo = recomendationsTo;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
