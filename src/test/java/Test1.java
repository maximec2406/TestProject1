import org.junit.Test;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test1 {

    @Test
    public void createUser(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersisUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction();

        User user = new User("testUser");
//        try {

            System.out.println("1");
            em.persist(user);
            System.out.println("2");
            em.getTransaction().commit();


//        } catch (Exception e) {
//            System.out.println("ФЕЙЛ!" + e.getMessage() + "\n" + e.getStackTrace());
//            e.getStackTrace();
//            StackTraceElement
//
//            t.rollback();
//            throw e;
//
//        }
    }
}
