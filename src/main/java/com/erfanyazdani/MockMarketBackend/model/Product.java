package com.erfanyazdani.MockMarketBackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "subCategory_id",nullable = false)
    private SubCategory subCategory;
    @ManyToOne
    @JoinColumn(name = "brand_id",nullable = false)
    private Brand brand;

    @Column
    private String thumbnailImage; // Property for the thumbnail image link
    @Column
    private String imageLinks;
    @Column
    private String shortDescription;
    @Column
    private String longDescription;
    @Column
    private Float price;
    @Column
    private boolean isOffer;
    @Column
    private Float offerPrice;
    @Column
    private Integer salePercentage;
    @Column
    private String details;
    @Column
    private Integer soldCount;
    @Column
    private Integer visitCount;
    @Column
    private Integer quantity;
    @Column
    private LocalDateTime date;
    @Column
    private Float rate;


    public Product() {}
    public Product(long id, String name, SubCategory subCategory, Brand brand, String thumbnailImage, String imageLinks, String shortDescription, String longDescription, Float price, boolean isOffer, Float offerPrice, Integer salePercentage, String details, Integer soldCount, Integer visitCount, Integer quantity, LocalDateTime date, Float rate) {
        Id = id;
        this.name = name;
        this.subCategory = subCategory;
        this.brand = brand;
        this.thumbnailImage = thumbnailImage;
        this.imageLinks = imageLinks;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.isOffer = isOffer;
        this.offerPrice = offerPrice;
        this.salePercentage = salePercentage;
        this.details = details;
        this.soldCount = soldCount;
        this.visitCount = visitCount;
        this.quantity = quantity;
        this.date = date;
        this.rate = rate;
    }


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDecription) {
        this.shortDescription = shortDecription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDecription) {
        this.longDescription = longDecription;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

//    public boolean isOffer() {
//        return isOffer;
//    }
//
//    public void setOffer(boolean offer) {
//        isOffer = offer;
//    }

    public boolean getIsOffer() {
        return isOffer;
    }
    public void SetIsOffer(boolean offer) {
        isOffer = offer;
    }

    public Float getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Float offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Integer getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(Integer salePercentage) {
        this.salePercentage = salePercentage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
