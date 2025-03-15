package com.erfanyazdani.MockMarketBackend.repository;

import com.erfanyazdani.MockMarketBackend.model.Like;
import com.erfanyazdani.MockMarketBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface LikeRepo extends JpaRepository<Like,Long> {
    @Query("SELECT l FROM Like l WHERE l.user.id = :userId AND l.product.id = :productId")
    Like findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query("SELECT l.product FROM Like l WHERE l.user.id = :userId AND l.isLiked")
    List<Product> findProductsByUserId(@Param("userId") Long userId);

}
