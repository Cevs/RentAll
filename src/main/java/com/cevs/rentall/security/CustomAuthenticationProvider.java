package com.cevs.rentall.security;

import com.cevs.rentall.dao.UserDao;
import com.cevs.rentall.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    UserDao userDao;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        try{
            User user = userDao.findUserByEmail(auth.getName());
            //Authorization process
            final Authentication result = super.authenticate(auth);
            return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
        }catch (Exception ex){
            throw new BadCredentialsException("Invalid username or password");
        }

    }
}
