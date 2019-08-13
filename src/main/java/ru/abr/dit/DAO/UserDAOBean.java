package ru.abr.dit.DAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Models.Author;
import ru.abr.dit.Models.Role;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDAOBean {

    //@Autowired раньше был Autowired, но после добавления управлением транзакциями для однозначности заменили @Autowired на @PersistanceContext
    @PersistenceContext
    private EntityManager em;

    public boolean checkUserNickname(String nickname){

        Boolean result = true;

        try {
            em.createQuery("from User where nickname =:nickname", User.class).setParameter("nickname", nickname).getSingleResult();
        } catch (EmptyResultDataAccessException e) {
            result = false;
        } catch (Exception e) {
            System.out.println("Неизвестный Exception в UserDAOBean");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean saveAuthor (Author a) {

        boolean result = false;

        try {
            em.persist(a);
            result = true;
        } catch (Exception e){
            result = false;
        }

        return result;
    }



    public List<Role> getUserRoles(int userId){
        return em.createQuery("from TP1_USER_ROLE where user_id =:userId").setParameter("userId", userId).getResultList();
    }

    public User findUserByEmail(String email){
        try {
            User user = (User) em.createQuery("from User where email =:email").setParameter("email", email).getSingleResult();
            return user;
        } catch (EntityNotFoundException e){
            return null;
        } catch (Exception e){
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
