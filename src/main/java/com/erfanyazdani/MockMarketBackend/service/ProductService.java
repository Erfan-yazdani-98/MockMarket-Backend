package com.erfanyazdani.MockMarketBackend.service;

import com.erfanyazdani.MockMarketBackend.dto.FilterListResponse;
import com.erfanyazdani.MockMarketBackend.dto.ProductRequest;
import com.erfanyazdani.MockMarketBackend.dto.ProductResponse;
import com.erfanyazdani.MockMarketBackend.model.Brand;
import com.erfanyazdani.MockMarketBackend.model.Product;
import com.erfanyazdani.MockMarketBackend.model.SubCategory;
import com.erfanyazdani.MockMarketBackend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private BrandService brandService;


    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Optional<Product> getById(long id) {
        return productRepo.findById(id);
    }



    public ResponseEntity<ProductResponse> getSelectedProducts(ProductRequest request) {
        ProductResponse response = new ProductResponse();
        System.out.println(request.getIsAscending()? "asc": "dsc");
        Sort sort = Sort.by(request.getIsAscending()? Sort.Direction.ASC: Sort.Direction.DESC,request.getSortingColumn()!=null?request.getSortingColumn():"Id");
        switch (request.getSelectingType()) {
            case "subcategory" -> {
                SubCategory subCategory = subCategoryService.getById(request.getInputId()).orElse(null);
                if (subCategory == null) {
                    response.message="the Indicated value as the subCategory Id is not valid";
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }
                Page<Product> products = productRepo.getProductsBySubCategoryAndFilters(request.getPriceMin(),request.getPriceMax(),request.getInStockItems(),request.getInputId(),request.getBrandIdsList(),PageRequest.of(request.getPage(), request.getSize(),sort));

                if (products.isEmpty()) {
                    response.message="there is no product in this subCategory";
                    return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
                }
                response.products=products.getContent();
                response.selectionTypeTitle=subCategory.getCategory().getName()+" > "+subCategory.getName();
                response.totalPages=products.getTotalPages();
                response.totalProducts=(int)products.getTotalElements();
                response.currentPage=products.getNumber();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case "brand" -> {
                Brand brand = brandService.getById(request.getInputId()).orElse(null);
                if (brand == null) {
                    response.message="the Indicated value as the brand Id is not valid";
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }
                Page<Product> products = productRepo.getProductsByBrandAndFilters(request.getPriceMin(),request.getPriceMax(),request.getInStockItems(),request.getInputId(),PageRequest.of(request.getPage(), request.getSize(),sort));

                if (products.isEmpty()) {
                    response.message="there is no product with this brand";
                    return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
                }
                response.products=products.getContent();
                response.selectionTypeTitle="Brand: "+brand.getName();
                response.totalPages=products.getTotalPages();
                response.totalProducts=(int)products.getTotalElements();
                response.currentPage=products.getNumber();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case "search" -> {
                Page<Product> products = productRepo.getProductsBySearchAndFilters(request.getPriceMin(),request.getPriceMax(),request.getInStockItems(),request.getInputString(),request.getBrandIdsList(),PageRequest.of(request.getPage(), request.getSize(),sort));

                if (products.isEmpty()) {
                    response.message="no product contains the searched string";
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                response.products=products.getContent();
                response.selectionTypeTitle=request.getInputString()+" searched!";
                response.totalPages=products.getTotalPages();
                response.totalProducts=(int)products.getTotalElements();
                response.currentPage=products.getNumber();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            default -> {
                response.message="there's something wrong with your request";
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }}
    }

    public ResponseEntity<ProductResponse> getProductsWithOffer(){
        ProductResponse response = new ProductResponse();
        Page<Product> productPage= productRepo.findTop50ByIsOfferTrue(PageRequest.of(0, 20));
        response.products = productPage.getContent();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ProductResponse> getTopRated(){
        ProductResponse response = new ProductResponse();
        Page<Product> productPage = productRepo.findTopByOrderByRateDesc(PageRequest.of(0, 20));
        response.products = productPage.getContent();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    public ResponseEntity<ProductResponse> getBestSellers(){
        ProductResponse response = new ProductResponse();
        Page<Product> productPage = productRepo.findTopByOrderBySoldCountDesc(PageRequest.of(0, 20));
        response.products = productPage.getContent();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //functions for loading the filter section of listPage
    public ResponseEntity<FilterListResponse> getFilters(ProductRequest request){
        FilterListResponse response = new FilterListResponse();
        switch (request.getSelectingType()) {
            case "subcategory" ->{
                List<Object[]> results = productRepo.getMinMaxPricesBySubcategory(request.getInputId());
                response.priceMin = results.isEmpty() || results.getFirst()[0] == null ? 0 : ((Number) results.getFirst()[0]).intValue();
                response.priceMax = results.isEmpty() || results.getFirst()[1] == null ? 5000 : ((Number) results.getFirst()[1]).intValue();
                response.brands = productRepo.findBrandsBySubcategory(request.getInputId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case "search"->{
                List<Object[]> results = productRepo.getMinMaxPricesBySearch(request.getInputString());
                response.priceMin = results.isEmpty() || results.getFirst()[0] == null ? 0 : ((Number) results.getFirst()[0]).intValue();
                response.priceMax = results.isEmpty() || results.getFirst()[1] == null ? 5000 : ((Number) results.getFirst()[1]).intValue();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case "brand" ->{
                List<Object[]> results = productRepo.getMinMaxPricesByBrand(request.getInputId());
                response.priceMin = results.isEmpty() || results.getFirst()[0] == null ? 0 : ((Number) results.getFirst()[0]).intValue();
                response.priceMax = results.isEmpty() || results.getFirst()[1] == null ? 5000 : ((Number) results.getFirst()[1]).intValue();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            default -> {
                response.message="there's something wrong with your request";
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
        }
    }

}
