package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.abr.dit.Beans.FormBeans.CreateEditUserBean;
import ru.abr.dit.DAO.UserDAOBean;
import ru.abr.dit.Models.User;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Transactional
public class UserController {

    @Autowired
    UserDAOBean userDAOBean;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }



    @GetMapping(path = "/newUser")
    public String getNewUserForm(@ModelAttribute(name = "newUser") CreateEditUserBean form){
        return "CreateUser";
    }

    @PostMapping(path = "/createUser")
    public String createUser(@Valid @ModelAttribute(name = "newUser") CreateEditUserBean form, BindingResult br, ModelMap model){

        //уникальный логин
        if (userDAOBean.findUserByEmail(form.getEmail()) != null)
            br.addError(new FieldError("newUser","email","Такой email уже существует"));
        if (userDAOBean.findUserByNickname(form.getNickname()) != null)
            br.addError(new FieldError("newUser","nickname","Такой nickname уже существует"));
        if (userDAOBean.findUserByNickname(form.getNickname()) != null)
            br.addError(new FieldError("newUser","nickname","Такой nickname уже существует"));
        if(!form.getSecPassword().equals(form.getPassword()))
            br.addError(new FieldError("newUser", "secPassword", "Пароль и подтверждение пароля не совпадают"));
        if(br.hasErrors())
            return "CreateUser";
        else {
            User user = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()), form.getNickname());
            user.setAbout(form.getAbout());
            user.setBirthday(form.getBirthday());
            user.setFirst_name(form.getFirst_name());
            user.setLast_name(form.getLast_name());
            user.setPhoto(form.getPhoto());
            try{
                userDAOBean.createUser(user);
                return "redirect:/";
            } catch (Exception e){
                System.out.println(e.getMessage());
                return "failAuthorSave";
            }
        }
    }
}
