package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.TestBean;

@Controller
public class SecondController {

    @Autowired
    private TestBean bean;

    @RequestMapping(value = "/getUser")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("bean",bean);
        return modelAndView;
    }
}
