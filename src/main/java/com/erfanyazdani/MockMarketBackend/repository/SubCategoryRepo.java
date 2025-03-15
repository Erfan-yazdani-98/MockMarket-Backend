package com.erfanyazdani.MockMarketBackend.repository;

import com.erfanyazdani.MockMarketBackend.model.Category;
import com.erfanyazdani.MockMarketBackend.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin
public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer> {
    Optional<SubCategory> findByName(String name);
    List<SubCategory> findByCategory(Category category);
}
