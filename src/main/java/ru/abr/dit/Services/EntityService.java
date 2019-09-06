package ru.abr.dit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.*;

import java.util.List;

@Service
public class EntityService {

    @Autowired
    MainDAO md;

    enum Languages{
        Русский, Английский, Французский, Испанский, Итальянский, Японский;
    }
    public Languages[] languagesFields(){
        return Languages.values();
    }



//    Author start

    public List<Author> getAllAuthorList(){ return md.getAllAuthorList() ; }

    public boolean createAuthor(Author author) { return md.createAuthor(author) ; }

    public List<String> getAuthorsNames() {return md.getAuthorsNames() ; }

    public Author getAutorByName(String name) {return md.getAutorByName(name) ; }

    public Author findAutorById(int id){return md.findAutorById(id); }

//    Author end

//    Book start

    public List<Book> getBookList(){
        return md.getAllBookList();
    }

    public void createBook(Book book) { md.createBook(book) ; }

    public boolean hasBookName(String name){ return md.getBookByName(name) != null ; }

    public Book findBookById(int id) { return md.findBookById(id); }

    public boolean updateBook (Book book) {return md.updateBook(book); }

//    Book end

//    User start

    public User findUserByEmail(String email) {return md.findUserByEmail(email) ; }

    public User findUserByNickname(String nickname) { return md.findUserByNickname(nickname) ; }

    public boolean createUser(User user) {return md.createUser(user); }

    public List<User> getAllUserList() { return md.getAllUserList();}

    public User findUserById(int id) { return md.findUserById(id); }

    public boolean deleteUser(User user) {return md.deleteUser(user); }

    public boolean updateUser(User user) {return md.updateUser(user); }

//    User end

//    Genre start

    public List<Genre> getGenreList(){return md.getAllGenreList(); }

    public List<String> getGenresNames(){return md.getGenresNames(); }

    public Genre getGenreByName(String name){
        return md.getGenreByName(name);
    }

    public boolean hasGenreName(String name){ return md.getGenreByName(name) != null ; }

    public void createGenre(Genre genre){ md.createGenre(genre); }

    public boolean deleteGenre(int id) {return md.deleteGenre(id); }

    public boolean updateGenre (Genre genre) {return md.updateGenre(genre); }

    public Genre getGenreById (int id) {return  md.getGenreById(id); }

//    Genre end

//    Roles start

    public List<String> getRolesNames() {return md.getRolesNames(); }

    public Role getRoleByName(String name) { return md.getRoleByName(name); }

//    Roles end
}


