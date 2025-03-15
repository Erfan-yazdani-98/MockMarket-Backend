package com.erfanyazdani.MockMarketBackend.dto;

import com.erfanyazdani.MockMarketBackend.model.Brand;

import java.util.List;

public class FilterListResponse {
    public String message;
    public Integer priceMin;
    public Integer priceMax;
    public List<Brand> brands;


    public FilterListResponse() {}

    public FilterListResponse(String message, Integer priceMin, Integer priceMax, List<Brand> brands) {
        this.message = message;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.brands = brands;
    }
}
