package ru.abr.dit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @RequestMapping(path = "/manageBook")
    public ModelAndView getBook(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("book");
        return mv;
    }
}
