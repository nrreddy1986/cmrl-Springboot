package com.shellinfo.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String unit;
    private String image;
    private String description;
    private double originalPrice; // for discount
    private int stock;
    private boolean isAvailable;
    private double rating;
    private int reviewCount;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
}
