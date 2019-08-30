package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.LoginPageBean;
import ru.abr.dit.Models.User;
import ru.abr.dit.Services.EntityService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private EntityService es;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/login")
    public String getLoginPage(@ModelAttribute(name = "loginForm")LoginPageBean form){
        return "login";
    }

    @PostMapping(path="/login")
    public ModelAndView signIn(@Valid @ModelAttribute(name = "loginForm")LoginPageBean form, BindingResult br){

        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        if(!br.hasErrors()) {
            User user = es.findUserByEmail(form.getUsername());
            if (user != null)
                if (passwordEncoder.matches(form.getPassword(),user.getEncryptedPassword()))
                    model.setViewName("home");
                else {
                    br.addError(new FieldError("loginForm", "errorMessage", "Неверный логин или пароль"));
                    System.out.println("Неверный пароль");
                }
            else {
                br.addError(new FieldError("loginForm", "errorMessage", "Неверный логин или пароль"));
                System.out.println("Неверный логин");
            }
        }
        return model;
    }
}
