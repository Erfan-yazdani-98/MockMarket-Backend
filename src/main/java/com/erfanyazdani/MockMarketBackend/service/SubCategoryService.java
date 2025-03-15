package com.erfanyazdani.MockMarketBackend.service;


import com.erfanyazdani.MockMarketBackend.model.Category;
import com.erfanyazdani.MockMarketBackend.model.SubCategory;
import com.erfanyazdani.MockMarketBackend.repository.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {
    @Autowired
    private SubCategoryRepo subCategoryRepo;
    public List<SubCategory> getAll() {
        System.out.println("getCategories");
        return this.subCategoryRepo.findAll();
    }


    public Optional<SubCategory> getById(int id) {
        return this.subCategoryRepo.findById(id);  // Assuming your CategoryRepo extends JpaRepository
    }

    public Optional<SubCategory> getByName(String name) {
        return this.subCategoryRepo.findByName(name);  // Assuming you have this method defined in your repository
    }
    public List<SubCategory> getByCategory(Category category) {
        return this.subCategoryRepo.findByCategory(category);  // Assuming you have this method defined in your repository
    }

    public SubCategory saveSubCategory(SubCategory subcat) {
        return this.subCategoryRepo.save(subcat);
    }
}
