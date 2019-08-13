package ru.abr.dit.Beans.FormBeans;


import javax.validation.constraints.Size;

public class LoginPageBean {

    @Size(min = 1, message = "Поле не должно быть пустым")
    private String username;

    @Size(min = 1, message = "Поле не должно быть пустым")
    private String password;

    private String errorMessage;

    public LoginPageBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
