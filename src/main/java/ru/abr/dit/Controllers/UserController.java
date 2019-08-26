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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.CreateEditUserBean;
import ru.abr.dit.DAO.UserDAOBean;
import ru.abr.dit.Models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class UserController {

    @Autowired
    UserDAOBean userDAOBean;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<String> roles;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @GetMapping(path = "/createUser")
    public ModelAndView getNewUserForm(@ModelAttribute(name = "newUser") CreateEditUserBean form, HttpServletRequest request){
        System.out.println("!!!!!!!!!!!!");
        System.out.println("Юлия");
        System.out.println(request.getCharacterEncoding());
        System.out.println(Charset.defaultCharset().displayName());

        ModelAndView model = new ModelAndView();
        model.setViewName("CreateUser");
        model.addObject("Roles", userDAOBean.getRolesNames());
        return model;
    }

    @PostMapping(path = "/createUser")
    public String createUser(@Valid @ModelAttribute(name = "newUser") CreateEditUserBean form, BindingResult br, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {


        System.out.println("!!!!!!!!!!!!");
        System.out.println("Юлия");
        System.out.println(request.getCharacterEncoding());
        System.out.println(Charset.defaultCharset().displayName());
        System.out.println(response.getCharacterEncoding());

        System.out.println(form.getFirst_name());
        //уникальный логин
        if (userDAOBean.findUserByEmail(form.getEmail()) != null)
            br.addError(new FieldError("newUser","email","Такой email уже существует"));
        if (userDAOBean.findUserByNickname(form.getNickname()) != null)
            br.addError(new FieldError("newUser","nickname","Такой nickname уже существует"));
        if(!form.getSecPassword().equals(form.getPassword()))
            br.addError(new FieldError("newUser", "secPassword", "Пароль и подтверждение пароля не совпадают"));
        if(br.hasErrors()) {
            model.addAttribute("Roles", userDAOBean.getRolesNames());
            return "CreateUser";
        }
        else {
            User user = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()), form.getNickname(), userDAOBean.getRoleByName(form.getRole()));
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
