package com.erfanyazdani.MockMarketBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subcategories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name= "category_id",
            nullable = false
    )
    private Category category;

    public SubCategory() {}

    public SubCategory(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
