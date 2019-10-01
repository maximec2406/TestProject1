package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.abr.dit.Beans.FormBeans.BookFormBean;
import ru.abr.dit.Models.Book;
import ru.abr.dit.Models.Genre;
import ru.abr.dit.Services.EntityService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public ModelAndView getCreateBookForm(@ModelAttribute (name="bookModel") BookFormBean form){
        ModelAndView model = new ModelAndView();
        model.addObject("regime", "Create");
        addObjectsToModel(model);
        form.setId(0);
        model.setViewName("book");
        return model;
    }

    @GetMapping(path = "/editBook")
    public ModelAndView getEditBookForm(@ModelAttribute (name="bookModel") BookFormBean form, @RequestParam int id){

        ModelAndView model = new ModelAndView();
        model.setViewName("book");
        model.addObject("regime","Edit");
        addObjectsToModel(model);
        Book book = es.findBookById(id);
        if (book != null) {
            bookToForm(book, form);
            model.addObject("AuthorName",book.getAuthor().getFullName());
        } else
            model.addObject("regime","Create");
        return model;
    }


    @PostMapping(path = "/saveBook")
    public ModelAndView createBook(@Validated @ModelAttribute (name="bookModel") BookFormBean form, BindingResult br){

        ModelAndView model = new ModelAndView();
        model.setViewName("book");
        model.addObject("regime","Edit");
        addObjectsToModel(model);

        // 0 - сущность новая
        if (form.getId() != 0){

            Book book = es.findBookById(form.getId());

            if(es.hasBookName(form.getName()) && !form.getName().equals(book.getName()))
                br.addError(new FieldError("bookModel","name","Книга с таким наименованием уже существует"));

            if (!br.hasErrors()){
                formToBook(form, book);

                if (es.updateBook(book))
                    model.addObject("saveResult", "Изменения сохранены");
                else {
                    br.addError(new FieldError("bookModel", "errorMessage", "Не удалось сохранить Книгу"));
                }
            }
        } else {

            if(es.hasBookName(form.getName()))
                br.addError(new FieldError("bookModel","name","Книга с таким наименованием уже существует"));

            if (!br.hasErrors()) {
                try {
                    es.createBook(new Book(form.getName(),
                            form.getDiscription(),
                            form.getYear(),
                            form.getOriginal_lang(),
                            es.findAutorById(Integer.valueOf(form.getAuthor()).intValue()),
                            es.getGenreByName(form.getGenre())
                    ));
                    model.clear();
                    model.addObject("books", es.getBookList());
                    model.addObject("regime", "List");
                    model.setViewName("book");
                } catch (Exception e) {
                    br.addError(new FieldError("bookModel", "errorMessage", "Не удалось сохранить Книгу"));
                    model.addObject("regime","Create");
                }
            } else
                model.addObject("regime","Create");
        }
        return model;
    }

    @PostMapping(path = "/deleteBook")
    public void deleteBook(@ModelAttribute BookFormBean form, HttpServletResponse response) throws IOException {
        try {
            es.deleteBookById(form.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        response.sendRedirect("book");
    }

    public void addObjectsToModel(ModelAndView model){
        model.addObject("Languages", es.languagesFields());
        model.addObject("Genres", es.getGenresNames());
        model.addObject("Authors", es.getAllAuthorList());
    }

    public void bookToForm(Book book, BookFormBean form){

        form.setAuthor(book.getAuthor().getId());
        form.setDiscription(book.getDiscription());
        form.setGenre(book.getGenres().get(0).getName());
        form.setName(book.getName());
        form.setOriginal_lang(book.getOriginal_lang());
        form.setYear(book.getYear());
    }

    public void formToBook(BookFormBean form, Book book){

        book.setAuthor(es.findAutorById(Integer.valueOf(form.getAuthor()).intValue()));
        book.setDiscription(form.getDiscription());
        List<Genre> g = new ArrayList<Genre>();
        g.add(es.getGenreByName(form.getGenre()));
        book.setGenres(g);
        book.setName(form.getName());
        book.setOriginal_lang(form.getOriginal_lang());
        book.setYear(form.getYear());
    }
}
