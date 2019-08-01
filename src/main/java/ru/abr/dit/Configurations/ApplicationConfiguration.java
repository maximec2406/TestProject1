package ru.abr.dit.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.abr.dit.Beans.TestBean;
import ru.abr.dit.DAO.MainDAOBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@EnableWebMvc
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

    @Bean
    public MainDAOBean getMainDAOBean(){
        return new MainDAOBean();
    }

    @Bean
    public ViewResolver createViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

}
