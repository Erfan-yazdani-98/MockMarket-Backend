package com.erfanyazdani.MockMarketBackend.service;

import com.erfanyazdani.MockMarketBackend.model.Brand;
import com.erfanyazdani.MockMarketBackend.repository.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrandRepo brandRepo;

    public List<Brand> getAll() {
        return brandRepo.findAll();
    }

    public Optional<Brand> getById(int id) {
        return brandRepo.findById(id);
    }

    public Optional<Brand> getByName(String name) {
        return brandRepo.findByName(name);
    }

    public Brand saveBrand(Brand brand) {
        return this.brandRepo.save(brand);
    }
}
