package com.erfanyazdani.MockMarketBackend.service;

import com.erfanyazdani.MockMarketBackend.dto.ProductResponse;
import com.erfanyazdani.MockMarketBackend.model.Bookmark;
import com.erfanyazdani.MockMarketBackend.model.Product;
import com.erfanyazdani.MockMarketBackend.model.User;
import com.erfanyazdani.MockMarketBackend.repository.BookmarkRepo;
import com.erfanyazdani.MockMarketBackend.repository.ProductRepo;
import com.erfanyazdani.MockMarketBackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkService {
    @Autowired
    BookmarkRepo bookmarkRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;

    public Bookmark getBookmark(Long userId, Long productId) {
        return bookmarkRepo.findByUserIdAndProductId(userId, productId);
    }

    public Bookmark addBookmark(Long userId, Long productId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<Product> product = productRepo.findById(productId);
        if(user.isEmpty() || product.isEmpty())
            return null;
        Bookmark bookmark = new Bookmark(user.get(),product.get(),true);

        return bookmarkRepo.save(bookmark);
    }

    public Bookmark addOrUpdateBookmark(Long userId, Long productId, boolean isBookmarked) {
        Bookmark existingBookmark = bookmarkRepo.findByUserIdAndProductId(userId, productId);
        if (existingBookmark != null) {
            existingBookmark.setIsBookmarked(isBookmarked);
            return bookmarkRepo.save(existingBookmark); // Save the updated bookmark
        }

        // If no bookmark exists, create a new one
        Optional<User> user = userRepo.findById(userId);
        Optional<Product> product = productRepo.findById(productId);

        if (user.isPresent() && product.isPresent()) {
            Bookmark newBookmark = new Bookmark();
            newBookmark.setUser(user.get());
            newBookmark.setProduct(product.get());
            newBookmark.setIsBookmarked(isBookmarked);
            return bookmarkRepo.save(newBookmark); // Save the new bookmark
        }

        return null; // Return null if either user or product is not found
    }

    public ResponseEntity<ProductResponse> getBookmarkList(Long userId) {
        ProductResponse response = new ProductResponse();
        response.products=bookmarkRepo.findProductsByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
