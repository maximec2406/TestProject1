package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.TestBean;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.User;

import java.util.ArrayList;

@Controller
public class SecondController {

    @Autowired
    private TestBean bean;

    @RequestMapping(value = "/getUser")
    public ModelAndView main() {

        // создаю тестовых пользователей

        User user1 = new User("fu");
        User user2 = new User("su");
        MainDAO.saveUser(user1);
        MainDAO.saveUser(user2);
//        ArrayList<User> users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("bean",bean);
        return modelAndView;
    }
}
