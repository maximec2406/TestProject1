package ru.abr.dit.Services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String getJSPParamForRole(Authentication authentication) {

        if (authentication != null) {

            for (GrantedAuthority i : authentication.getAuthorities()) {
                switch (i.getAuthority()) {
                    case "ROLE_ADMIN":
                        return "ADMIN";
                    case "ROLE_USER":
                        return "USER";
                    default:
                        return null;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
