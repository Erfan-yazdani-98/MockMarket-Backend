package com.erfanyazdani.MockMarketBackend.dto;

import com.erfanyazdani.MockMarketBackend.model.Product;

import java.util.List;

public class ProductResponse {
    public String message;
    public List<Product> products;
    public String selectionTypeTitle;

    public int totalPages; // Total pages
    public int totalProducts; // Total products count
    public int currentPage;
}
