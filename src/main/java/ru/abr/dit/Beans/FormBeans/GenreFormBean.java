package ru.abr.dit.Beans.FormBeans;

import javax.validation.constraints.NotEmpty;

public class GenreFormBean {

    private int id;

    @NotEmpty(message = "Не должно быть пустым")
    private String name;

    @NotEmpty(message = "Не должно быть пустым")
    private String discription;

    private String errorMessage;

    public GenreFormBean() {
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
