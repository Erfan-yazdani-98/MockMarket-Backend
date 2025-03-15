package com.erfanyazdani.MockMarketBackend.conroller;


import com.erfanyazdani.MockMarketBackend.dto.SubCategoryRequest;
import com.erfanyazdani.MockMarketBackend.model.Category;
import com.erfanyazdani.MockMarketBackend.model.SubCategory;
import com.erfanyazdani.MockMarketBackend.service.CategoryService;
import com.erfanyazdani.MockMarketBackend.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<SubCategory>> getAll() {
        List<SubCategory> categories = subCategoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getById(@PathVariable int id) {
        return subCategoryService.getById(id)
                .map(subCategory -> new ResponseEntity<>(subCategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SubCategory> getByName(@PathVariable String name) {
        return subCategoryService.getByName(name)
                .map(subCategory -> new ResponseEntity<>(subCategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SubCategory>> getByCategoryId(@PathVariable int categoryId) {
        Category category = categoryService.getById(categoryId).orElse(null);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<SubCategory> subCategories = subCategoryService.getByCategory(category);

        // Check if the list is empty
        if (subCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategoryRequest request) {
        String subCategoryName = request.getSubCategoryName();
        int theId = request.getCategoryId();
        if (subCategoryName == null || subCategoryName.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Category cat = categoryService.getById(theId).orElse(null);
        if (cat == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SubCategory subCat = new SubCategory(subCategoryName,cat);
        SubCategory savedCategory = subCategoryService.saveSubCategory(subCat);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }


}
