package ru.abr.dit.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.abr.dit.Beans.TestBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@ComponentScan("ru.abr.dit")
@Configuration
//@EnableAutoConfiguration
public class ApplicationConfiguration {

    @Bean
    public TestBean getTestBean(){
        return new TestBean("OLOLO");
    }

    @Bean
    public EntityManager getEntityManager(@Autowired EntityManagerFactory emf){
        return emf.createEntityManager();
    }

    @Bean
    public EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("TestPersisUnit");
    }

}
