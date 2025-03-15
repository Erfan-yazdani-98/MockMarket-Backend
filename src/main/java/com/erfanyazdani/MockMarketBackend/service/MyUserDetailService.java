package com.erfanyazdani.MockMarketBackend.service;

import com.erfanyazdani.MockMarketBackend.model.User;
import com.erfanyazdani.MockMarketBackend.model.UserPrincipal;
import com.erfanyazdani.MockMarketBackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repo.findByUsername(username);
        if(user==null){
            System.out.println("user is not found!" + username);
            throw new UsernameNotFoundException("user is not found!" + username);
        }
        return new UserPrincipal(user);
    }
}
