package ru.abr.dit.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.Models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class MainDAOBean {

    @PersistenceContext
    private EntityManager em;

    public MainDAOBean() {
    }

    public void saveUser(User user) {
        em.persist(user);
        // допиши метод нормально
    }

    public List getUsers(){
        return em.createQuery("From User").getResultList();

    }
}
