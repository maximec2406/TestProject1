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
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.User;
import ru.abr.dit.Services.EntityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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

    private List<String> roles;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping(path = "/admin/user")
    public ModelAndView getUserList(){
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/user");
        addObjectsToModel(model);
        return model;
    }

    @GetMapping(path = "/createUser")
    public ModelAndView getNewUserForm(@ModelAttribute(name = "userModel") CreateEditUserBean form){
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/user");
        addObjectsToModel(model);
        model.addObject("regime","Create");
        return model;
    }

    @PostMapping(path = "/createUser")
    public String createUser(@Valid @ModelAttribute(name = "userModel") CreateEditUserBean form,
                             BindingResult br,
                             ModelMap model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody String url) {

        // уникальный логин
        if (es.findUserByEmail(form.getEmail()) != null)
            br.addError(new FieldError("userModel","email","Такой email уже существует"));
        // уникальный никнейм
        if (es.findUserByNickname(form.getNickname()) != null)
            br.addError(new FieldError("userModel","nickname","Такой nickname уже существует"));
        //совпадение паролей
        if(!form.getSecPassword().equals(form.getPassword()))
            br.addError(new FieldError("userModel", "secPassword", "Пароль и подтверждение пароля не совпадают"));

        if(br.hasErrors()) {
            model.addAttribute("Roles", es.getRolesNames());
        } else {
            User user = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()), form.getNickname(), es.getRoleByName(form.getRole()));
            user.setAbout(form.getAbout());
            user.setBirthday(form.getBirthday());
            user.setFirst_name(form.getFirst_name());
            user.setLast_name(form.getLast_name());
            user.setPhoto(form.getPhoto());
            try{
                es.createUser(user);
                addObjectsToModel(model);
            } catch (Exception e){
                br.addError(new FieldError("userModel", "errorMessage", "Не удалось сохранить Пользователя"));
                addObjectsToModel(model);
            }
        }
        return "admin/user";
    }

    public void addObjectsToModel(ModelAndView model){
        model.addObject("Roles", es.getRolesNames());
        model.addObject("users",es.getAllUserList());
        model.addObject("regime","List");
    }

    public void addObjectsToModel(ModelMap model){
        model.addAttribute("Roles", es.getRolesNames());
        model.addAttribute("users",es.getAllUserList());
        model.addAttribute("regime","List");
    }
}
