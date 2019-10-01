package ru.abr.dit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Beans.FormBeans.AuthorFormBean;
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
        } catch (Exception e) {
            System.out.println("MainDAO. Не найден автор. " + e.getMessage() );
            return null;
        }
    }

    public Author findAutorById(int id){
        try{
            return (Author) em.createQuery("from Author where id=:id").setParameter("id", id).getSingleResult();
        } catch (Exception e){
            System.out.println("MainDAO.findAutorById. " + e.getMessage());
            return null;
        }
    }
    public List<String> getAuthorsNames(){ return em.createQuery("select last_name from Author").getResultList() ; }

    public boolean hasSameAuthor(AuthorFormBean form){
        try{
            if (em.createQuery("from Author where id!=:id and last_name=:last_name and first_name=:first_name and birthday=:birthday")
                    .setParameter("id", form.getId())
                    .setParameter("last_name", form.getLast_name())
                    .setParameter("first_name", form.getFirst_name())
                    .setParameter("birthday", form.getBirthday())
                    .getResultList()
                    .size() > 0)
                return true;
            else
                return false;
        } catch (NoResultException e){
            System.out.println("MainDAO.hasSameAuthor. " + e.getMessage());
            return false;
        } catch (EntityNotFoundException e){
            System.out.println("MainDAO.hasSameAuthor. " + e.getMessage());
            return false;
        }
        catch (Exception e){
            System.out.println("MainDAO.hasSameAuthor. " + e.getMessage());
            return true;
        }
    }

    public boolean saveAuthor(Author author) {
        try{
            em.merge(author);
            return true;
        } catch (Exception e){
            System.out.println("MainDAO.saveAuthor. " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAuthor(int id){
        try{
            em.remove(findAutorById(id));
            return true;
        } catch (Exception e){
            System.out.println("MainDAO.deleteAuthor. " + e.getMessage());
            return false;
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

    public Book findBookById(int id) {
        try{
            return (Book) em.createQuery("from Book where id=:id").setParameter("id", id).getSingleResult();
        } catch (Exception e){
            System.out.println("MainDAO.findBookById. " + e.getMessage());
            return null;
        }
    }

    public boolean updateBook(Book book){
        try{
            em.merge(book);
            return true;
        } catch (Exception e) {
            System.out.println("MainDAO.updateBook. " + e.getMessage());
            return false;
        }

    }

    public boolean deleteBookById(int id) {
        try{
            em.remove(findBookById(id));
            return true;
        } catch (Exception e) {
            System.out.println("MainDAO.deleteBook. " + e.getMessage());
            return false;
        }
    }

//    Book end

//    Genre start

    public List<Genre> getAllGenreList(){return em.createQuery("from Genre order by name").getResultList() ; }
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

    public Genre getGenreById(int Id) {
        try {
            return (Genre) em.createQuery("from Genre where id=:id").setParameter("id", Id).getSingleResult();

        } catch (Exception e) {
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



    public boolean deleteGenre(int id) {

        try {
            em.remove(getGenreById(id));
            return true;
        } catch (Exception e) {
            System.out.println("MainDAO.deleteGenre. " + e.getMessage());
            return false;
        }
    }


    public boolean updateGenre(Genre genre){
        try {
            em.merge(genre);
        } catch (Exception e){
            System.out.println("MainDAO.updateGenre. " + e.getMessage());
            return false;
        }
        return true;
    }

//    Genre end

//    User start

    public User findUserById(int id) {
        try{
            return (User) em.createQuery("from User where id=:id").setParameter("id", id).getSingleResult();
        } catch (Exception e){
            System.out.println("MainDAO.findUserById. " + e.getMessage());
            return null;
        }
    }

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

    public boolean editUser(int id) {
        try{
            em.merge(findUserById(id));
            return true;
        } catch (Exception e){
            System.out.println("MainDAO.deleteUser. " + e.getMessage());
            return false;
        }
    }
    public boolean deleteUser(User user) {
        try{
            em.remove(user);
            return true;
        } catch (Exception e){
            System.out.println("MainDAO.deleteUser. " + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user){
        try {
            em.merge(user);
        } catch (Exception e){
            System.out.println("MainDAO.updateUser. " + e.getMessage());
            return false;
        }
        return true;

    }

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
