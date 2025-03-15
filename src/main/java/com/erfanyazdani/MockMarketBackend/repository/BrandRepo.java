package com.erfanyazdani.MockMarketBackend.repository;

import com.erfanyazdani.MockMarketBackend.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin
public interface BrandRepo extends JpaRepository<Brand,Integer> {
    Optional<Brand> findByName(String name);
}
