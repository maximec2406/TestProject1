package ru.abr.dit.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ru.abr.dit.Models.Author;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;

@Component
public class UserDAOBean {

    @Autowired
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
        em.getTransaction().begin();

        try {
            em.persist(a);
            em.getTransaction().commit();
            result = true;
        } catch (Exception e){
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            result = false;
        }

        return result;

    }
}
