package com.erfanyazdani.MockMarketBackend.conroller;

import com.erfanyazdani.MockMarketBackend.dto.ProductResponse;
import com.erfanyazdani.MockMarketBackend.model.Bookmark;
import com.erfanyazdani.MockMarketBackend.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;


    ///bookmarks?userId=1&productId=1
    @GetMapping
    public ResponseEntity<Bookmark> getBookmark(@RequestParam Long userId, @RequestParam Long productId) {
        Bookmark bookmark = bookmarkService.getBookmark(userId, productId);
        if (bookmark != null) {
            return ResponseEntity.ok(bookmark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //bookmarks?userId=1&productId=1&isBookmarked=true
    @PostMapping
    public ResponseEntity<Bookmark> addOrUpdateBookmark(@RequestParam Long userId, @RequestParam Long productId, @RequestParam boolean isBookmarked) {
        Bookmark updatedOrNewBookmark = bookmarkService.addOrUpdateBookmark(userId, productId, isBookmarked);

        if (updatedOrNewBookmark != null) {
            return ResponseEntity.ok(updatedOrNewBookmark); // Return the updated or new bookmark
        } else {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if user or product is missing
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ProductResponse> getBookmarkList(@RequestParam Long userId) {
        return bookmarkService.getBookmarkList(userId);
    }

}
