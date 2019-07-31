package ru.abr.dit.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;


public class MainDAO {

    @Autowired
    private static EntityManager em;

    public static void saveUser(User user) {

        System.out.println(user.getLogin());

    }
}
