package com.erfanyazdani.MockMarketBackend.service;

import com.erfanyazdani.MockMarketBackend.model.Category;
import com.erfanyazdani.MockMarketBackend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryService() {}

    public List<Category> getAll() {
        return this.categoryRepo.findAll();
    }

    public Optional<Category> getById(int id) {
        return this.categoryRepo.findById(id);  // Assuming your CategoryRepo extends JpaRepository
    }

    public Optional<Category> getByName(String name) {
        return this.categoryRepo.findByName(name);  // Assuming you have this method defined in your repository
    }

    public Category saveCategory(Category cat) {
        return this.categoryRepo.save(cat);
    }
}