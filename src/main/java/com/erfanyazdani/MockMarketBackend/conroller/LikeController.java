package com.erfanyazdani.MockMarketBackend.conroller;

import com.erfanyazdani.MockMarketBackend.dto.ProductResponse;
import com.erfanyazdani.MockMarketBackend.model.Like;
import com.erfanyazdani.MockMarketBackend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    LikeService likeService;


    ///likes?userId=1&productId=1
    @GetMapping
    public ResponseEntity<Like> getLike(@RequestParam Long userId, @RequestParam Long productId) {
        Like like = likeService.getLike(userId, productId);
        if (like != null) {
            return ResponseEntity.ok(like);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //likes?userId=1&productId=1&isLiked=true
    @PostMapping
    public ResponseEntity<Like> addOrUpdateLike(@RequestParam Long userId, @RequestParam Long productId, @RequestParam boolean isLiked) {
        Like updatedOrNewLike = likeService.addOrUpdateLike(userId, productId, isLiked);

        if (updatedOrNewLike != null) {
            return ResponseEntity.ok(updatedOrNewLike); // Return the updated or new bookmark
        } else {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if user or product is missing
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ProductResponse> getLikeList(@RequestParam Long userId) {
        return likeService.getLikeList(userId);
    }

}
