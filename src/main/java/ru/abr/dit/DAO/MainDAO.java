package ru.abr.dit.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MainDAO {

    @Autowired
    private static EntityManager em;

    public static void saveUser(User user) {

        EntityTransaction t = em.getTransaction();
        try {
            em.persist(user);
            t.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            t.rollback();
            throw e;

        }
    }
}
