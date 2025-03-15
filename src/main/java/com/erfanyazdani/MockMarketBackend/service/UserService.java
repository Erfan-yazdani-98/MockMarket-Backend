package com.erfanyazdani.MockMarketBackend.service;

import com.erfanyazdani.MockMarketBackend.model.User;
import com.erfanyazdani.MockMarketBackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService() {
    }

    public User saveUser(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        return (User)this.userRepo.save(user);
    }

    public User getUser(User user) {
        User retUser = this.userRepo.findByUsername(user.getUsername());
        return retUser != null && this.encoder.matches(user.getPassword(), retUser.getPassword()) ? retUser : null;
    }

}
