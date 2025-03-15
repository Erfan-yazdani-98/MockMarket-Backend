package com.erfanyazdani.MockMarketBackend.conroller;

import com.erfanyazdani.MockMarketBackend.dto.ProductRequest;
import com.erfanyazdani.MockMarketBackend.dto.ProductResponse;
import com.erfanyazdani.MockMarketBackend.model.Product;
import com.erfanyazdani.MockMarketBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //It seems I had to make this POST for sending requestBody for being concordant to the standards
    //requestBody=null => getAll
    @PostMapping
    public ResponseEntity<ProductResponse> getProducts(@RequestBody(required = false) ProductRequest request) {
        if (request==null){
            ProductResponse response = new ProductResponse();
            response.products=productService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if(request.getSelectingType()==null){
            ProductResponse response = new ProductResponse();
            response.products=productService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            return productService.getSelectedProducts(request);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        return productService.getById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/offer")
    public ResponseEntity<ProductResponse> getOffers() {
        return productService.getProductsWithOffer();
    }
    @GetMapping("/bestseller")
    public ResponseEntity<ProductResponse> getBestSellers() {
        return productService.getBestSellers();
    }
    @GetMapping("/popular")
    public ResponseEntity<ProductResponse> getMostPopulars() {
        return productService.getTopRated();
    }

    @GetMapping("/hello")
    public String greetGet(){
        return "Hello from get";
    }
    @PostMapping("/hello")
    public String greetPost(){
        return "Hello from post";
    }
}
