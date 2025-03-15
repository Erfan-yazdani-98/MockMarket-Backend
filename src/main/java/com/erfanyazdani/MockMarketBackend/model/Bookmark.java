package com.erfanyazdani.MockMarketBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name="bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private boolean isBookmarked;

    public Bookmark() {}

    public Bookmark(User user, Product product, boolean isBookmarked) {
        this.user = user;
        this.product = product;
        this.isBookmarked = isBookmarked;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
