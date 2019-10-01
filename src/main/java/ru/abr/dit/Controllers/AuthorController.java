package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.AuthorFormBean;
import ru.abr.dit.Enums.EnumCountry;
import ru.abr.dit.Models.Author;
import ru.abr.dit.Services.EntityService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AuthorController {

    @Autowired
    private EntityService es;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping(path="/author")
    public ModelAndView addAuthor(@ModelAttribute(name = "authorModel") AuthorFormBean form){

        ModelAndView model = new ModelAndView();
        model.addObject("regime", "List");
        model.addObject("authors", es.getAllAuthorList());
        model.setViewName("author");
        model.addObject("EnumCountry", EnumCountry.values());
        return model;
    }

    @GetMapping(path="/createAuthor")
    public ModelAndView getCreateAuthorForm(@ModelAttribute(name = "authorModel") AuthorFormBean form){

        ModelAndView model = new ModelAndView();
        form.setId(0);
        model.addObject("regime", "Create");
        model.setViewName("author");
        model.addObject("EnumCountry", EnumCountry.values());
        return model;
    }

    @GetMapping(path="/editAuthor")
    public ModelAndView getEditAuthorForm(@ModelAttribute(name = "authorModel") AuthorFormBean form, @RequestParam int id){

        ModelAndView model = new ModelAndView();
        authorToForm(es.findAutorById(id), form);
        model.addObject("regime", "Edit");
        model.setViewName("author");
        model.addObject("EnumCountry", EnumCountry.values());
        return model;
    }

    @PostMapping(path="/saveAuthor")
    public ModelAndView createAuthor(@Valid @ModelAttribute(name = "authorModel") AuthorFormBean form, BindingResult br){

        ModelAndView model = new ModelAndView();
        model.addObject("EnumCountry", EnumCountry.values());
        model.addObject("regime", "Edit");
        model.addObject("authors", es.getAllAuthorList());
        model.setViewName("author");

        if(es.hasSameAuthor(form))
            br.addError(new FieldError("authorModel","last_name","Автор с таким именем, фамилией и датой рождения уже существует"));

        // если 0 - сущность новая
        if (form.getId() !=0){
            Author author = es.findAutorById(form.getId());

//            if(es.hasSameAuthor(form))
//                br.addError(new FieldError("authorModel","last_name","Автор с таким именем, фамилией и датой рождения уже существует"));

            if (!br.hasErrors()) {
                formToAuthor(form, author);
                model.addObject("saveResult", (es.saveAuthor(author)) ? "Изменения успешно сохранены" : "Изменения сохранить не удалось");
            }
        } else {

            if (!br.hasErrors()) {
                try {

                    Author author = formToNewAuthor(form);
                    form.setId(author.getId());
                    es.createAuthor(author);
                    form.setId(author.getId());
                    model.addObject("saveResult", "Изменения успешно сохранены");
                } catch (Exception e) {
                    br.addError(new FieldError("authorModel", "errorMessage", "Не удалось сохранить Автора"));
                    model.addObject("regime", "Create");
                    System.out.println(e.getMessage());
                }
            }
        }
        return model;
    }


    @PostMapping(path = "/deleteAuthor")
    public void deleteAuthor(@ModelAttribute(name = "authorModel") AuthorFormBean form, HttpServletResponse response) throws IOException {

        es.deleteAuthor(form.getId());
        response.sendRedirect("author");

    }

    public void formToAuthor(AuthorFormBean form, Author author) {

        author.setAbout(form.getAbout());
        author.setBirthday(form.getBirthday());
        author.setPatronymic(form.getPatronymic());
        author.setCountry(EnumCountry.valueOf(form.getCountry()));
        author.setDeathday(form.getDeathday());
        author.setFirst_name(form.getFirst_name());
        author.setLast_name(form.getLast_name());
        author.setPhoto(form.getPhoto());

    }

    public Author formToNewAuthor(AuthorFormBean form) {

        Author author = new Author(form.getFirst_name(),
                form.getLast_name(),
                form.getPatronymic(),
                form.getBirthday(),
                form.getDeathday(),
                form.getAbout(),
                form.getPhoto(),
                EnumCountry.valueOf(form.getCountry()));

        return author;
    }

    public void authorToForm(Author author, AuthorFormBean form){

        form.setBirthday(author.getBirthday());
        form.setPatronymic(author.getPatronymic());
        form.setCountry(author.getCountry().name());
        form.setDeathday(author.getDeathday());
        form.setFirst_name(author.getFirst_name());
        form.setLast_name(author.getLast_name());
        form.setAbout(author.getAbout());
        form.setPhoto(author.getPhoto());
        form.setId(author.getId());
    }
}
