package com.erfanyazdani.MockMarketBackend.repository;


import com.erfanyazdani.MockMarketBackend.model.Brand;
import com.erfanyazdani.MockMarketBackend.model.Product;
import com.erfanyazdani.MockMarketBackend.model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin
public interface ProductRepo extends JpaRepository<Product,Long> {
//    Optional<Product> findByName(String name);
//    List<Product> findBySubCategory(SubCategory subCategory, Sort sort);
//    List<Product> findByBrand(Brand brand,Sort sort);


    //    getProductsBySubCategoryAndFilters
    @Query("SELECT p FROM Product p " +
            "JOIN p.brand b " +
            "JOIN p.subCategory s " +
            "WHERE (:priceMin IS NULL OR p.price >= :priceMin) " +
            "AND  (:priceMax IS NULL OR p.price <= :priceMax) " +
            "AND (:inStockItems IS NULL OR (p.quantity > 0 AND :inStockItems = true) OR (:inStockItems = false)) " +
            "AND (:brandIdsList IS NULL OR b.id IN :brandIdsList)" +
            "AND (:subCategoryId IS NULL OR s.id = :subCategoryId)")
    Page<Product> getProductsBySubCategoryAndFilters(
            @Param("priceMin") Integer priceMin,
            @Param("priceMax") Integer priceMax,
            @Param("inStockItems") Boolean inStockItems,
            @Param("subCategoryId") Integer subCategoryId,
            @Param("brandIdsList") List<Integer> brandIdsList,
            Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.rate DESC")
    Page<Product> findTopByOrderByRateDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.soldCount DESC")
    Page<Product> findTopByOrderBySoldCountDesc(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.isOffer = true")
    Page<Product> findTop50ByIsOfferTrue(Pageable pageable);

//    getProductsBySearchAndFilters
    @Query("SELECT p FROM Product p " +
            "JOIN p.brand b " +
            "WHERE (:priceMin IS NULL OR p.price >= :priceMin) " +
            "AND  (:priceMax IS NULL OR p.price <= :priceMax) " +
            "AND (:inStockItems IS NULL OR (p.quantity > 0 AND :inStockItems = true) OR (:inStockItems = false)) " +
            "AND (:searchString IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :searchString, '%')) " +
            "OR LOWER(b.name) LIKE LOWER(CONCAT('%', :searchString, '%')))" +
            "AND (:brandIdsList IS NULL OR b.id IN :brandIdsList)")
    Page<Product> getProductsBySearchAndFilters(
            @Param("priceMin") Integer priceMin,
            @Param("priceMax") Integer priceMax,
            @Param("inStockItems") Boolean inStockItems,
            @Param("searchString") String searchString,
            @Param("brandIdsList") List<Integer> brandIdsList,
            Pageable pageable);



    //    getProductsByBrandAndFilters
    @Query("SELECT p FROM Product p " +
            "JOIN p.brand b " +
            "WHERE (:priceMin IS NULL OR p.price >= :priceMin) " +
            "AND  (:priceMax IS NULL OR p.price <= :priceMax) " +
            "AND (:inStockItems IS NULL OR (p.quantity > 0 AND :inStockItems = true) OR (:inStockItems = false)) " +
            "AND (:brandId IS NULL OR b.id = :brandId)")
    Page<Product> getProductsByBrandAndFilters(
            @Param("priceMin") Integer priceMin,
            @Param("priceMax") Integer priceMax,
            @Param("inStockItems") Boolean inStockItems,
            @Param("brandId") Integer brandId,
            Pageable pageable);



    //Functions for loading the filters of the listPage

    @Query("SELECT MIN(p.price), MAX(p.price) FROM Product p " +
            "JOIN p.brand b " +
            "WHERE (:brandId IS NULL OR b.id = :brandId)")
    List<Object[]> getMinMaxPricesByBrand(
            @Param("brandId") Integer brandId);


    @Query("SELECT MIN(p.price), MAX(p.price) FROM Product p " +
            "JOIN p.brand b " +
            "JOIN p.subCategory s " +
            "WHERE (:subCategoryId IS NULL OR s.id = :subCategoryId)")
    List<Object[]> getMinMaxPricesBySubcategory(@Param("subCategoryId") Integer subCategoryId);

    @Query("SELECT DISTINCT b FROM Product p " +
            "JOIN p.brand b " +
            "JOIN p.subCategory s " +
            "WHERE (:subCategoryId IS NULL OR s.id = :subCategoryId)")
    List<Brand> findBrandsBySubcategory(@Param("subCategoryId") Integer subCategoryId);


    @Query("SELECT MIN(p.price), MAX(p.price) FROM Product p " +
            "JOIN p.brand b " +
            "WHERE (:searchString IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :searchString, '%')) " +
            "OR LOWER(b.name) LIKE LOWER(CONCAT('%', :searchString, '%')))")
    List<Object[]> getMinMaxPricesBySearch(@Param("searchString") String searchString);




}
