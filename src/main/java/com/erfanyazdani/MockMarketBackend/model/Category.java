package com.erfanyazdani.MockMarketBackend.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table (name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(nullable = false)
    private String name;

    public Category() {
    }

    // Constructor with parameters (optional)
    public Category(String name) {
        this.name = name;
    }

    // Getters and setters
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
