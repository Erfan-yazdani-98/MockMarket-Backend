package com.erfanyazdani.MockMarketBackend.dto;

import java.util.List;

public class ProductRequest {
    //the values for indicating the type inquiry and their value. "subcategory","search","brand"
    //the get request could be by SubCategory:Id(int) or Search(String) or Brand:Id(int)
    private String selectingType;
    private Integer inputId;
    private String inputString;

    //the values for sorting
    private String sortingColumn;
    private Boolean isAscending;

    //Filter values
    private Boolean inStockItems;
    private Integer priceMin;
    private Integer priceMax;
    private List<Integer> brandIdsList;

    //pagination values
    private Integer page; // For current page
    private Integer size; // For page size

//prepared empty JSON
//    {
//        "selectingType": null,
//            "inputId": null,
//            "inputString": null,
//            "sortingColumn": null,
//            "isAscending": null,
//            "inStockItems": null,
//            "priceMin": null,
//            "priceMax": null,
//            "brandIdsList": null,
//            "page": null,
//            "size": null,
//    }


//    {
//        "selectingType": "search",
//            "inputId": 1,
//            "inputString": "a",
//            "sortingColumn": "price",
//            "isAscending": false,
//            "inStockItems": null,
//            "priceMin": 100,
//            "priceMax": 950,
//            "brandIdsList": [1, 3]
//            "page": 0,
//            "size": 30,
//    }



    //Constructors
    public ProductRequest() {}
    public ProductRequest(String selectingType, Integer inputId, String inputString, String sortingColumn, Boolean isAscending, Boolean inStockItems, Integer priceMin, Integer priceMax, List<Integer> brandIdsList) {
        this.selectingType = selectingType;
        this.inputId = inputId;
        this.inputString = inputString;
        this.sortingColumn = sortingColumn;
        this.isAscending = isAscending;
        this.inStockItems = inStockItems;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.brandIdsList = brandIdsList;
    }

    //Getters & Setters


    public String getSelectingType() {
        return selectingType;
    }

    public void setSelectingType(String selectingType) {
        this.selectingType = selectingType;
    }

    public Integer getInputId() {
        return inputId;
    }

    public void setInputId(Integer inputNumber) {
        this.inputId = inputNumber;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getSortingColumn() {
        return sortingColumn;
    }

    public void setSortingColumn(String sortingColumn) {
        this.sortingColumn = sortingColumn;
    }

    public Boolean getIsAscending() {
        return isAscending!=null?isAscending:false;
    }

    public void setIsAscending(Boolean isAscending) {
        this.isAscending = isAscending;
    }

    public Boolean getInStockItems() {
        return inStockItems;
    }

    public void setInStockItems(Boolean inStockItems) {
        this.inStockItems = inStockItems;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public List<Integer> getBrandIdsList() {
        return brandIdsList;
    }

    public void setBrandIdsList(List<Integer> brandIdsList) {
        this.brandIdsList = brandIdsList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "selectingType='" + selectingType + '\'' +
                ", inputId=" + inputId +
                ", inputString='" + inputString + '\'' +
                ", sortingColumn='" + sortingColumn + '\'' +
                ", isAscending=" + isAscending +
                ", inStockItems=" + inStockItems +
                ", priceMin=" + priceMin +
                ", priceMax=" + priceMax +
                ", brandIdsList=" + brandIdsList +
                '}';
    }
}
