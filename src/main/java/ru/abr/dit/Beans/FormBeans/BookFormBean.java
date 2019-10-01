package ru.abr.dit.Beans.FormBeans;

import ru.abr.dit.Models.Author;

import javax.validation.constraints.NotEmpty;

public class BookFormBean {

    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String discription;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String year;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String original_lang;

    private int author;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String genre;

    private String errorMessage;

    public BookFormBean() {
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

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
