package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.TestBean;
import ru.abr.dit.DAO.MainDAOBean;
import ru.abr.dit.Models.User;
import java.util.List;

@Controller
public class SecondController {

    @Autowired
    private TestBean bean;

    @Autowired
    private MainDAOBean mainDAOBean;

    @RequestMapping(value = "/getUser")
    public ModelAndView main() {

        User user1 = new User("Zh");
        mainDAOBean.saveUser(user1);
        mainDAOBean.saveUser(new User("Vasya"));

        List<User> users = null;

        try {
             users = mainDAOBean.getUsers();
        } catch (Exception e) {
            System.out.println("УУУУУУУУУУУУПС");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("bean",bean);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    // чтобы не забыть, как вытаскивать параметры запросов
    @RequestMapping(value = "/addUser")
    public String postAddUserForm(@RequestParam String kind, String login, ModelMap model) {
        String login1=login;
        return login1;
    }
}
