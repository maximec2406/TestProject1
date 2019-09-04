package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.CreateEditBookBean;
import ru.abr.dit.Models.Book;
import ru.abr.dit.Services.EntityService;

@Controller
public class BookController {

    @Autowired
    EntityService es;

    @GetMapping(path = "/book")
    public ModelAndView getBooksList() {
        ModelAndView model = new ModelAndView();
        model.addObject("regime","List");
        model.addObject("books", es.getBookList());
        model.setViewName("book");
        return model;
    }

    @GetMapping(path = "/createBook")
    public ModelAndView getCreateBookForm(@ModelAttribute (name="bookModel")CreateEditBookBean form){
        ModelAndView model = new ModelAndView();
        addObjectsToModel(model);
        model.setViewName("book");
        return model;
    }

    @PostMapping(path = "/createBook")
    public ModelAndView createBook(@Validated @ModelAttribute (name="bookModel") CreateEditBookBean form, BindingResult br){

        ModelAndView model = new ModelAndView();

        if(es.hasBookName(form.getName()))
            br.addError(new FieldError("newBook","name","Книга с таким наименованием уже существует"));

        if (!br.hasErrors()) {
            try {
                es.createBook(new Book(form.getName(),
                        form.getDiscription(),
                        form.getYear(),
                        form.getOriginal_lang(),
                        es.getAutorByName(form.getAuthor()),
                        es.getGenreByName(form.getGenre())
                ));
                model.addObject("books", es.getBookList());
                model.addObject("regime", "List");
            } catch (Exception e) {
                br.addError(new FieldError("bookModel", "errorMessage", "Не удалось сохранить Книгу"));
                addObjectsToModel(model);
            }
        } else
            addObjectsToModel(model);

        model.setViewName("book");
        return model;
    }

    public void addObjectsToModel(ModelAndView model){
        model.addObject("regime", "Create");
        model.addObject("Languages", es.languagesFields());
        model.addObject("Genres", es.getGenresNames());
        model.addObject("Authors", es.getAuthorsNames());
    }
}
