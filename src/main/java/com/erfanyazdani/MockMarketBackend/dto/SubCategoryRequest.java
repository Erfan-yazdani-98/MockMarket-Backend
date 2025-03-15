package com.erfanyazdani.MockMarketBackend.dto;

public class SubCategoryRequest {
    private String subCategoryName;
    private int categoryId;

    public SubCategoryRequest() {}
    public SubCategoryRequest(String subCategoryName, int categoryId) {
        this.subCategoryName = subCategoryName;
        this.categoryId = categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
