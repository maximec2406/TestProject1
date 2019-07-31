package ru.abr.dit.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.abr.dit.Beans.TestBean;
import ru.abr.dit.DAO.MainDAOBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@ComponentScan("ru.abr.dit.*")
@Configuration
public class ApplicationConfiguration {

    @Bean
    public TestBean getTestBean(){
        return new TestBean("OLOLO");
    }

    @Bean
    public EntityManager createEntityManager(@Autowired EntityManagerFactory emf){
        return emf.createEntityManager();
    }

    @Bean
    public EntityManagerFactory createEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("PromPersistUnit");
    }

    public MainDAOBean getMainDAOBean(){
        return new MainDAOBean();
    }

}
