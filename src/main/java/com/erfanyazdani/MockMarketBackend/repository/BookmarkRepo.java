package com.erfanyazdani.MockMarketBackend.repository;

import com.erfanyazdani.MockMarketBackend.model.Bookmark;
import com.erfanyazdani.MockMarketBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface BookmarkRepo extends JpaRepository<Bookmark,Long> {

    @Query("SELECT b FROM Bookmark b WHERE b.user.id = :userId AND b.product.id = :productId")
    Bookmark findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query("SELECT b.product FROM Bookmark b WHERE b.user.id = :userId AND b.isBookmarked")
    List<Product> findProductsByUserId(@Param("userId") Long userId);

}
