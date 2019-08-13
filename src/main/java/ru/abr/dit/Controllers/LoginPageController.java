package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.abr.dit.Beans.FormBeans.LoginPageBean;
import ru.abr.dit.DAO.UserDAOBean;
import ru.abr.dit.Models.User;

import javax.validation.Valid;

@Controller
public class LoginPageController {

    @Autowired
    private UserDAOBean userDAOBean;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String getLoginPage(@ModelAttribute(name = "loginForm")LoginPageBean form){
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, path="/login")
    public String signIn(@Valid @ModelAttribute(name = "loginForm")LoginPageBean form, BindingResult br, ModelMap model){

        String result = "login";
        if(!br.hasErrors()) {
            User user = userDAOBean.findUserByEmail(form.getUsername());
            if (user != null)
                if (passwordEncoder.matches(form.getPassword(),user.getEncryptedPassword()))
                    result = "home";
                else
                    br.addError(new FieldError("loginForm", "errorMessage", "Неверный пароль"));
            else
                br.addError(new FieldError("loginForm", "errorMessage", "Неверный логин"));
        }
        return result;
    }
}
