package ru.abr.dit.Beans.FormBeans;

import ru.abr.dit.Models.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class CreateEditUserBean {

    @NotEmpty (message = "Поле не должно быть пустым")
    @Size (min=3, message = "Значение в поле слишком короткое")
    private String email;

    @Size (min=3, message = "Значение в поле слишком короткое")
    private String password;

    private String secPassword;

    @NotEmpty (message = "Поле не должно быть пустым")
    @Size (min=3, message = "Значение в поле слишком короткое")
    private String nickname;

    @NotEmpty (message = "Поле не должно быть пустым")
    @Size (min=3, message = "Значение в поле слишком короткое")
    private String first_name;

    @NotEmpty (message = "Поле не должно быть пустым")
    @Size (min=3, message = "Значение в поле слишком короткое")
    private String last_name;

    @Past(message = "Дата не может быть больше и равной текущей")
    private Date birthday;

    private String about;

    private String photo;

    private String errorMessage;

    @NotEmpty(message = "Значени не выбрано")
    private String role;

    public CreateEditUserBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecPassword() {
        return secPassword;
    }

    public void setSecPassword(String secPassword) {
        this.secPassword = secPassword;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
