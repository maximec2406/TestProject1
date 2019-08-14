package ru.abr.dit.DAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Models.Author;
import ru.abr.dit.Models.Role;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UserDAOBean {

    //@Autowired раньше был Autowired, но после добавления управлением транзакциями для однозначности заменили @Autowired на @PersistanceContext
    @PersistenceContext
    private EntityManager em;

    public User findUserByNickname(String nickname){
        try {
            User user = (User) em.createQuery("from User where nickname =:nickname", User.class).setParameter("nickname", nickname).getSingleResult();
            return user;
        } catch (EmptyResultDataAccessException e) {
            return  null;
        } catch (EntityNotFoundException e){
            return null;
        } catch (Exception e) {
            System.out.println("Неизвестный Exception в UserDAOBean");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User findUserByEmail(String email){
        try {
            User user = (User) em.createQuery("from User where email =:email").setParameter("email", email).getSingleResult();
            return user;
        } catch (EmptyResultDataAccessException e) {
            return  null;
        } catch (EntityNotFoundException e){
            return null;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean createUser (User user) {

        boolean result = false;

        try {
            em.persist(user);
            result = true;
        } catch (Exception e){
            result = false;
        }

        return result;
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



    public List<Role> getRoles(){
        return em.createQuery("from Role").getResultList();
    }

    public List<String> getRolesNames(){
        return em.createQuery("select name from Role").getResultList();
    }

    public Role getRoleByName(String roleName){

        try{
            Role role = (Role) em.createQuery("from Role where name =:roleName").setParameter("roleName",roleName).getSingleResult();
            return role;
        } catch (EmptyResultDataAccessException e) {
            return  null;
        } catch (EntityNotFoundException e){
            System.out.println("Не найдена роль " + roleName);
            return null;
        } catch (Exception e) {
            System.out.println("Неизвестный Exception в UserDAOBean "  + roleName);
            System.out.println(e.getMessage());
            return null;
        }
    }

//    Старая версия метода, до включения @Transactional
//    public boolean saveAuthor (Author a) {
//
//        boolean result = false;
//
//        em.getTransaction().begin();
//
//        try {
//            em.persist(a);
//            em.getTransaction().commit();
//            result = true;
//        } catch (Exception e){
//            em.getTransaction().rollback();
//            System.out.println(e.getMessage());
//            result = false;
//        }
//
//        return result;
//    }
}
