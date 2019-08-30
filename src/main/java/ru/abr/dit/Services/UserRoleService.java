package ru.abr.dit.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abr.dit.DAO.MainDAO;
import ru.abr.dit.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Service
@Transactional
public class UserRoleService implements UserDetailsService {

    @Autowired
    private MainDAO md;

    @PersistenceContext
    private EntityManager em;

    public UserDetails loadUserByUsername (String email)  throws UsernameNotFoundException {

        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();

        try {
            User user = md.findUserByEmail(email);

           switch (user.getRole().getName()){
               case "Администратор":
                   roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                   break;
               case "Пользователь":
                   roles.add(new SimpleGrantedAuthority("ROLE_USER"));
                   break;
           }
            return new org.springframework.security.core.userdetails.User(email,user.getEncryptedPassword(),roles);
        } catch (NoResultException notFound) {
            throw new UsernameNotFoundException("User " + email + "not found");
        }
    }
}
