package ru.abr.dit.Beans.FormBeans;

import javax.validation.constraints.NotEmpty;

public class CreateEditBookBean {

    @NotEmpty(message = "Поле не должно быть пустым")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String discription;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String year;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String original_lang;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String author;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String genre;

    private String errorMessage;

    public CreateEditBookBean() {
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

    public String getOriginal_lang() {
        return original_lang;
    }

    public void setOriginal_lang(String original_lang) {
        this.original_lang = original_lang;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genres) {
        this.genre = genres;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
