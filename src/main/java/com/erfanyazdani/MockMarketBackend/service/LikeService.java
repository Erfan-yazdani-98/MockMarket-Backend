package com.erfanyazdani.MockMarketBackend.service;

import com.erfanyazdani.MockMarketBackend.dto.ProductResponse;
import com.erfanyazdani.MockMarketBackend.model.Like;
import com.erfanyazdani.MockMarketBackend.model.Product;
import com.erfanyazdani.MockMarketBackend.model.User;
import com.erfanyazdani.MockMarketBackend.repository.LikeRepo;
import com.erfanyazdani.MockMarketBackend.repository.ProductRepo;
import com.erfanyazdani.MockMarketBackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    LikeRepo likeRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;

    public Like getLike(Long userId, Long productId) {
        return likeRepo.findByUserIdAndProductId(userId, productId);
    }

    public Like addOrUpdateLike(Long userId, Long productId, boolean isLiked) {
        Like existingLike = likeRepo.findByUserIdAndProductId(userId, productId);
        if (existingLike != null) {
            existingLike.setIsLiked(isLiked);
            return likeRepo.save(existingLike); // Save the updated like
        }

        // If no like exists, create a new one
        Optional<User> user = userRepo.findById(userId);
        Optional<Product> product = productRepo.findById(productId);

        if (user.isPresent() && product.isPresent()) {
            Like newLike = new Like();
            newLike.setUser(user.get());
            newLike.setProduct(product.get());
            newLike.setIsLiked(isLiked);
            return likeRepo.save(newLike); // Save the new like
        }

        return null; // Return null if either user or product is not found
    }

    public ResponseEntity<ProductResponse> getLikeList(Long userId) {
        ProductResponse response = new ProductResponse();
        response.products=likeRepo.findProductsByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
