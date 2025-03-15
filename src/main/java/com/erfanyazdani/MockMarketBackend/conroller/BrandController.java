package com.erfanyazdani.MockMarketBackend.conroller;

import com.erfanyazdani.MockMarketBackend.model.Brand;
import com.erfanyazdani.MockMarketBackend.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAll() {
        List<Brand> brands = brandService.getAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getById(@PathVariable int id) {
        return brandService.getById(id)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Brand> getByName(@PathVariable String name) {
        return brandService.getByName(name)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Brand> saveBrand(@RequestBody String brandName) {
        if (brandName == null || brandName.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Brand brand = new Brand(brandName);
        Brand savedBrand = brandService.saveBrand(brand);

        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

}
