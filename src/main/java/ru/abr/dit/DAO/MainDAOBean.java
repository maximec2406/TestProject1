package ru.abr.dit.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class MainDAOBean {

    @Autowired
    private EntityManager em;

    @Autowired
    private EntityManagerFactory emf;

    public MainDAOBean() {
    }

    public void saveUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public List getUsers(){
        return em.createQuery("From User").getResultList();

    }
}
