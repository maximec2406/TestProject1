package ru.abr.dit.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@EnableWebMvc
@ComponentScan("ru.abr.dit.*")
@Configuration
@EnableTransactionManagement //управление транзакциями, конфиг для методов @Transactional
//@Import(SecurityConfigeration.class)//связали конфиги
public class ApplicationConfiguration {

//    этот бин больше не нужен, даже опасен, т.к. используется управление транзакциями
//    @Bean
//    public EntityManager createEntityManager(@Autowired EntityManagerFactory emf){
//        return emf.createEntityManager();
//    }



    @Bean
    public EntityManagerFactory createEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("PromPersistUnit");
    }

    @Bean
    public ViewResolver createViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setContentType("charset=UTF-8");
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public PlatformTransactionManager transactionManager (@Autowired EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
