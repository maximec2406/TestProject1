package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Models.Genre;
import ru.abr.dit.Services.EntityService;

@Controller
public class AdminController {

    @Autowired
    EntityService es;

    @GetMapping(path = "/admin/admin")
    public String getAdmin(){
        return "admin/admin";
    }

    @GetMapping(path="/admin/genre")
    public ModelAndView getGenreList(){
        ModelAndView model = new ModelAndView();
        model.addObject("regim","List");
        model.addObject("genres", es.getGenreList());
        model.setViewName("admin/genre");
        return model;
    }

    @GetMapping(path="/createGenre")
    public ModelAndView getCreateGenre(@ModelAttribute(name = "genreModel") Genre form, BindingResult br){

        ModelAndView model = new ModelAndView();
        model.addObject("regim", "Create");
        model.setViewName("admin/genre");
        return model;
    }

    @PostMapping(path="/createGenre")
    public ModelAndView createGenre(@ModelAttribute(name = "genreModel")Genre form, BindingResult br){

        ModelAndView model = new ModelAndView();
        model.setViewName("admin/genre");

        if (es.hasGenreName(form.getName()))
            br.addError(new FieldError("genreModel","name","Жанр с таким наименованием уже существует"));
        if (!br.hasErrors()) {
            es.createGenre(new Genre(form.getName(), form.getDiscription()));
            model.addObject("regim", "List");
        } else
            model.addObject("regim", "Create");
        return model;
    }
}
