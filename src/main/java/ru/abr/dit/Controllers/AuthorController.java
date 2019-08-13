package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.AddAuthorFormBean;
import ru.abr.dit.DAO.UserDAOBean;
import ru.abr.dit.Enums.EnumCountry;
import ru.abr.dit.Models.Author;


import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AuthorController {

    @Autowired
    private UserDAOBean udb;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @Transactional
    @RequestMapping(path="/manageAuthor", method = RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute(name = "authorModel") AddAuthorFormBean form, BindingResult br, ModelMap model){

        model.addAttribute("EnumCountry", EnumCountry.values());

        // кастомные проверки
//        br.addError(new FieldError("authorModel", "first_name","Дай имя"));
//        br.addError(new FieldError("authorModel", "last_name","Give last_name"));
        if (br.hasErrors()){
            return "author";
        }

        Author a = new Author(form.getFirst_name(),form.getLast_name(), form.getPatronymic(), form.getBirthday(),form.getDeathday(),form.getAbout(),form.getPhoto(), EnumCountry.valueOf(form.getCountry()));

        return udb.createAuthor(a) ? "successAuthorSave" : "failAuthorSave";
    }


    @GetMapping(path="/manageAuthor")
    public ModelAndView addAuthor(@ModelAttribute(name = "authorModel") AddAuthorFormBean form){

        ModelAndView model = new ModelAndView();
        model.setViewName("author");
        model.addObject("EnumCountry", EnumCountry.values());
        return model;
    }
}
