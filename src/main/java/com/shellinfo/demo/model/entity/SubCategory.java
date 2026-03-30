package com.shellinfo.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sub_categories")
@Data
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private String description;

    @ManyToOne
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;

    private String slug;
    private Integer displayOrder;
    private Boolean isActive;
    private String bannerImage;
}