package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.AddAuthorFormBean;
import ru.abr.dit.Enums.EnumCountry;
import ru.abr.dit.Models.Author;
import ru.abr.dit.Services.EntityService;
import javax.validation.Valid;
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

    @GetMapping(path="/createAuthor")
    public ModelAndView getCreateAuthorForm(@ModelAttribute(name = "authorModel") AddAuthorFormBean form){

        ModelAndView model = new ModelAndView();
        model.addObject("regime", "Create");
//        model.addObject("authors", es.getAllAuthorList());
        model.setViewName("author");
        model.addObject("EnumCountry", EnumCountry.values());
        return model;
    }

    @PostMapping(path="/createAuthor")
    public String createAuthor(@Valid @ModelAttribute(name = "authorModel") AddAuthorFormBean form, BindingResult br, ModelMap model){

        model.addAttribute("EnumCountry", EnumCountry.values());

        if (!br.hasErrors()) {
            try {
                model.addAttribute("regime", "List");
                es.createAuthor(new Author(form.getFirst_name(),
                        form.getLast_name(),
                        form.getPatronymic(),
                        form.getBirthday(),
                        form.getDeathday(),
                        form.getAbout(),
                        form.getPhoto(),
                        EnumCountry.valueOf(form.getCountry()))
                );
                model.addAttribute("authors", es.getAllAuthorList());
            } catch (Exception e) {
                br.addError(new FieldError("authorModel", "errorMessage", "Не удалось сохранить Автора"));
                model.addAttribute("regime", "Create");
                System.out.println(e.getMessage());
            }
        }
        return "author";
    }


    @GetMapping(path="/author")
    public ModelAndView addAuthor(@ModelAttribute(name = "authorModel") AddAuthorFormBean form){

        ModelAndView model = new ModelAndView();
        model.addObject("regime", "List");
        model.addObject("authors", es.getAllAuthorList());
        model.setViewName("author");
        model.addObject("EnumCountry", EnumCountry.values());
        return model;
    }
}
