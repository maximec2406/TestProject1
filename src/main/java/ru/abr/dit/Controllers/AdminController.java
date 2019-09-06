package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.GenreFormBean;
import ru.abr.dit.Models.Genre;
import ru.abr.dit.Services.EntityService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        model.addObject("regime","List");
        model.addObject("genres", es.getGenreList());
        model.setViewName("admin/genre");
        return model;
    }

    @GetMapping(path="/createGenre")
    public ModelAndView getCreateGenreForm(@ModelAttribute(name = "genreModel") GenreFormBean form){

        form.setId(0);
        ModelAndView model = new ModelAndView();
        model.addObject("regime", "Create");
        model.setViewName("admin/genre");
        return model;
    }

    @GetMapping(path="/editGenre")
    public ModelAndView getEditGenreForm(@ModelAttribute(name = "genreModel") GenreFormBean form, @RequestParam int id){

        Genre genre = es.getGenreById(id);
        form.setName(genre.getName());
        form.setDiscription(genre.getDiscription());
        form.setId(genre.getId());
        ModelAndView model = new ModelAndView();
        model.addObject("regime", "Edit");
        model.setViewName("admin/genre");
        return model;
    }

    @PostMapping(path="/saveGenre")
    public ModelAndView saveGenre(@Validated @ModelAttribute(name = "genreModel") GenreFormBean form, BindingResult br, HttpServletResponse response) throws IOException {

        ModelAndView model = new ModelAndView();
        model.setViewName("admin/genre");
        model.addObject("regime", "Edit");

        // 0 - если сущность новая
        if (form.getId() != 0) {

            Genre genre = es.getGenreById(form.getId());

            if (genre != null) {
                genre.setDiscription(form.getDiscription());
                genre.setName(form.getName());

                if (es.updateGenre(genre))
                    model.addObject("saveResult", "Изменения сохранены");
                else
                    br.addError(new FieldError("genreModel", "errorMessage", "Не удалось обновить жанр"));

            } else {
                br.addError(new FieldError("genreModel", "errorMessage", "Не удалось обновить жанр. Жанр не найден"));
            }

        } else {
            if (es.hasGenreName(form.getName())) {
                br.addError(new FieldError("genreModel", "name", "Жанр с таким наименованием уже существует"));
            }

            if (!br.hasErrors()) {
                es.createGenre(new Genre(form.getName(), form.getDiscription()));
                model.addObject("saveResult", "Изменения сохранены");
            } else
                model.addObject("regime", "Create");
        }
        return model;
    }

    @PostMapping(path="/deleteGenre")
    public void deleteGenre(@ModelAttribute(name = "genreModel") GenreFormBean form, HttpServletResponse response) throws IOException {

        try{
            es.deleteGenre(form.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        response.sendRedirect("admin/genre");
    }
}
