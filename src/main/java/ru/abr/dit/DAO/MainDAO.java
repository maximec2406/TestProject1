package ru.abr.dit.DAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class MainDAO {

    @PersistenceContext
    private EntityManager em;

//    Author start

    public List<Author> getAllAuthorList(){
        try {
            return em.createQuery("from Author").getResultList();
        } catch (Exception e){
            System.out.println("MainDAO.getAllAuthorList(). " + e.getStackTrace());
            return null;
        }
    }

    public boolean createAuthor (Author a) {

        boolean result = false;

        try {
            em.persist(a);
            result = true;
        } catch (Exception e){
            result = false;
        }

        return result;
    }

    public Author getAutorByName(String name) {
        try {
            return (Author) em.createQuery("from Author where last_name=:name").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Не найден автор,");
            return null;
        } catch (EntityNotFoundException e) {
            System.out.println("Не найден автор");
            return null;
        }
    }

//    Author end


//    Book start

    public List<Book> getAllBookList(){
        return em.createQuery("from Book").getResultList() ;
    }

    public Book getBookByName(String name) {
        try {
            return (Book) em.createQuery("from Book where name=:name").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void createBook(Book book) {
        try {
            em.persist(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    Book end

//    Genre start

    public List<Genre> getAllGenreList(){return em.createQuery("from Genre").getResultList() ; }
    public List<String> getGenresNames() {return em.createQuery("select name from Genre").getResultList() ; }

    public Genre getGenreByName(String name) {
        try {
            return (Genre) em.createQuery("from Genre where name=:name").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Не найден жанр");
            return null;
        } catch (EntityNotFoundException e) {
            System.out.println("Не найден жанр");
            return null;
        }
    }

    public void createGenre (Genre genre){
        try {
            em.persist(genre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getAuthorsNames(){ return em.createQuery("select last_name from Author").getResultList() ; };

//    Genre end

//    User start

    public User findUserByNickname(String nickname){
        try {
            return (User) em.createQuery("from User where nickname =:nickname", User.class).setParameter("nickname", nickname).getSingleResult();
        } catch (Exception e) {
            System.out.println("MainDAO.findUserByNickname(). " + e.getMessage());
            return null;
        }
    }

    public User findUserByEmail(String email){
        try {
            return (User) em.createQuery("from User where email =:email").setParameter("email", email).getSingleResult();
        } catch (Exception e){
            System.out.println("MainDAO.findUserByEmail(). " + e.getMessage());
            return null;
        }
    }

    public boolean createUser (User user) {
        try {
            em.persist(user);
            return true;
        } catch (Exception e){
            System.out.println("MainDAO.CreateUser(). " + e.getMessage());
            return false;
        }
    }

    public List<User> getAllUserList() {return em.createQuery("from User").getResultList(); }

//    User end


//    Roles start

    public List<Role> getRoles(){
        return em.createQuery("from Role").getResultList();
    }

    public List<String> getRolesNames(){
        return em.createQuery("select name from Role").getResultList();
    }

    public Role getRoleByName(String roleName){
        try{
            return (Role) em.createQuery("from Role where name =:roleName").setParameter("roleName",roleName).getSingleResult();
        } catch (Exception e) {
            System.out.println("MainDAO.getRoleByName(). "  + roleName + ". " + e.getMessage());
            return null;
        }
    }


//    Roles end
}
