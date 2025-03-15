package com.erfanyazdani.MockMarketBackend.repository;

import com.erfanyazdani.MockMarketBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
