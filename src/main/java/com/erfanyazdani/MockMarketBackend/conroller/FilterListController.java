package com.erfanyazdani.MockMarketBackend.conroller;

import com.erfanyazdani.MockMarketBackend.dto.FilterListResponse;
import com.erfanyazdani.MockMarketBackend.dto.ProductRequest;
import com.erfanyazdani.MockMarketBackend.dto.ProductResponse;
import com.erfanyazdani.MockMarketBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("filterlist")
public class FilterListController {

    @Autowired ProductService productService;
    @PostMapping
    public ResponseEntity<FilterListResponse> getAll(@RequestBody ProductRequest request){
        return productService.getFilters(request);
    }
}
