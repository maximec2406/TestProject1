package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Services.SecurityService;

@Controller
public class HomeController {

    @Autowired
    SecurityService securityService;

    @PostMapping(path = "/home")
    public ModelAndView postHome(){

        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        model.addObject("ROLE",securityService.getJSPParamForRole(SecurityContextHolder.getContext().getAuthentication()));
        return model;
    }

    @GetMapping(path = "/home")
    public ModelAndView getHome(){

        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        model.addObject("ROLE",securityService.getJSPParamForRole(SecurityContextHolder.getContext().getAuthentication()));
        return model;
    }
}
