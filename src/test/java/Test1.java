import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ContextConfiguration(classes = ru.abr.dit.Configurations.ApplicationConfiguration.class)
public class Test1 {


    public void createUser(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User user = new User();
        user.setNickname("login1");

        try {
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("ФЕЙЛ!" + e.getMessage() + "\n" + e.getStackTrace());
            em.getTransaction().rollback();
        }
    }
}
