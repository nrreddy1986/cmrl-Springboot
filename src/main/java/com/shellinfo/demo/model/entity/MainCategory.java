package com.shellinfo.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "main_categories")
@Data
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private String description;

    private String slug;          // "grocery-kitchen" (for SEO / APIs)
    private Integer displayOrder; // UI sorting
    private Boolean isActive;     // enable/disable category
    private String icon;
}
