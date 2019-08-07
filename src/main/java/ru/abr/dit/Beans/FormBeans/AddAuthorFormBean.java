package ru.abr.dit.Beans.FormBeans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddAuthorFormBean {

    @NotNull(message = "Дай имя")
    @Size(min = 1, max = 5, message = "Give first name!")
    private String first_name;

    @NotNull (message = "Get last name")
    @Size(min = 1, max = 5, message = "Дай фамилию!")
    private String last_name;


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

}
