package ru.abr.dit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.abr.dit.Beans.FormBeans.AddAuthorFormBean;

import javax.validation.Valid;

@Controller
public class addAuthorController {

    @RequestMapping(path="/manageAuthor", method = RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute(name = "authorModel") AddAuthorFormBean form, BindingResult br, ModelMap model){
        br.addError(new FieldError("authorModel", "first_name","Дай имя"));
        br.addError(new FieldError("authorModel", "last_name","Give last_name"));
        return "author";
    }


    @GetMapping(path="/manageAuthor")
    public String addAuthor(@ModelAttribute(name = "authorModel") AddAuthorFormBean form){
        return "author";
    }
}
