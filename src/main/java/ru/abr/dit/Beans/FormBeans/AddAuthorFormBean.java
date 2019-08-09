package ru.abr.dit.Beans.FormBeans;


import javax.validation.constraints.*;
import java.util.Date;

public class AddAuthorFormBean {

    @NotEmpty(message = "Поле не может быть пустым")
    private String first_name;

    @NotEmpty(message = "Поле не может быть пустым")
    private String last_name;

    private String patronymic;

    @NotNull(message = "Поле не может быть пустым")
    @Past(message = "Дата рождения не может быть больше текущей даты")
    private Date birthday;

    @Future(message = "Дата смерти не может быть больше текущей даты")
    private Date deathday;

    @Size(min = 0, max = 2000, message = "Слишком длинный текст")
    private String about;

    private String photo;

    private String country;

    public AddAuthorFormBean() {
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
