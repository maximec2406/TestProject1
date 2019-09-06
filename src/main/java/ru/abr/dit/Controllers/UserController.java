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
import ru.abr.dit.Beans.FormBeans.UserFormBean;
import ru.abr.dit.Models.User;
import ru.abr.dit.Services.EntityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class UserController {

    @Autowired
    EntityService es;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping(path = "/admin/user")
    public ModelAndView getUserList(){

        ModelAndView model = new ModelAndView();
        model.addObject("Roles", es.getRolesNames());
        model.addObject("regime","List");
        model.addObject("users",es.getAllUserList());
        model.setViewName("admin/user");
        return model;
    }

    @GetMapping(path = "/createUser")
    public ModelAndView getNewUserForm(@ModelAttribute(name = "userModel") UserFormBean form){

        form.setId(0);
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/user");
        model.addObject("Roles", es.getRolesNames());
        model.addObject("regime","Create");
        return model;
    }

    @GetMapping(path = "/editUser")
    public ModelAndView getEditUserForm(@ModelAttribute(name = "userModel") UserFormBean form, @RequestParam int id){

        ModelAndView model = new ModelAndView();
        model.setViewName("admin/user");
        model.addObject("regime","Edit");
        model.addObject("Roles", es.getRolesNames());
        User user = es.findUserById(id);
        if (user != null) {
            userToForm(user, form);
        } else
            model.addObject("regime","Create");
        return model;
    }

    @PostMapping(path = "/saveUser")
    public String saveUser(@Valid @ModelAttribute(name = "userModel") UserFormBean form,
                             BindingResult br,
                             ModelMap model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody String url) {

        model.addAttribute("regime","Edit");
        model.addAttribute("Roles", es.getRolesNames());

        // 0 - если сущность новая
        if (form.getId() != 0) {

            User user = es.findUserById(form.getId());

            //проверка обновления пароля
            if (form.getPassword().length() > 0)
                pswChecks(form.getPassword(), form.getSecPassword(), br);

            //проверки обновленных nickname и email
            // уникальный логин
            if (!user.getEmail().equals(form.getEmail()) && es.findUserByEmail(form.getEmail()) != null)
                br.addError(new FieldError("userModel", "email", "Такой email уже существует"));
            // уникальный никнейм
            if (!user.getNickname().equals(form.getNickname()) && es.findUserByNickname(form.getNickname()) != null)
                br.addError(new FieldError("userModel", "nickname", "Такой nickname уже существует"));

            if (!br.hasErrors()) {
                formToUser(form, user);

                if (es.updateUser(user))
                    model.addAttribute("saveResult", "Изменения сохранены");
                else {
                    br.addError(new FieldError("userModel", "errorMessage", "Не удалось сохранить Пользователя"));
                }
            }
        } else {

            // для нового пользователя всегда проверям пароли
            pswChecks(form.getPassword(), form.getSecPassword(), br);
            if (!br.hasErrors()) {
                User user = formToNewUser(form);
                if (es.createUser(user)) {
                    model.addAttribute("saveResult", "Изменения сохранены");
                    form.setId(user.getId());
                }
                else {
                    br.addError(new FieldError("userModel", "errorMessage", "Не удалось сохранить Пользователя"));
                    model.addAttribute("regime","Create");
                }
            }
        }
        return "admin/user";
    }

    @PostMapping(path = "/deleteUser")
    public void deleteUser(@ModelAttribute UserFormBean form, HttpServletResponse response) throws IOException {
        try {
            es.deleteUser(es.findUserById(form.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        response.sendRedirect("admin/user");
    }

    public void pswChecks(String psw1, String psw2, BindingResult br){
        if (psw1.length() <3 )
            br.addError(new FieldError("userModel", "secPassword", "Слишком короткий"));
        if (!psw1.equals(psw2))
            br.addError(new FieldError("userModel", "secPassword", "Пароль и подтверждение пароля не совпадают"));

    }

    public void userToForm(User user,UserFormBean form){
        form.setId(user.getId());
        form.setAbout(user.getAbout());
        form.setBirthday(user.getBirthday());
        form.setEmail(user.getEmail());
        form.setFirst_name(user.getFirst_name());
        form.setLast_name(user.getLast_name());
        form.setNickname(user.getNickname());
        form.setPhoto(user.getPhoto());
        form.setRole(user.getRole().getName());
    }

    public void formToUser(UserFormBean form, User user){

        if (form.getPassword().length() > 0){
            user.setEncryptedPassword(passwordEncoder.encode(form.getPassword()));
        }
        user.setEmail(form.getEmail());
        user.setNickname(form.getNickname());
        user.setRole(es.getRoleByName(form.getRole()));
        user.setAbout(form.getAbout());
        user.setBirthday(form.getBirthday());
        user.setFirst_name(form.getFirst_name());
        user.setLast_name(form.getLast_name());
        user.setPhoto(form.getPhoto());

    }

    public User formToNewUser(UserFormBean form){

        User user = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()), form.getNickname(), es.getRoleByName(form.getRole()));
        user.setAbout(form.getAbout());
        user.setBirthday(form.getBirthday());
        user.setFirst_name(form.getFirst_name());
        user.setLast_name(form.getLast_name());
        user.setPhoto(form.getPhoto());

        return user;

    }
}
