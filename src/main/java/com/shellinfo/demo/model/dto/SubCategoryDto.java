package com.shellinfo.demo.model.dto;

import java.util.List;

public class SubCategoryDto {

    private Long id;
    private String name;
    private String image;
    private String description;
    private List<ProductDto> products;

    public SubCategoryDto(Long id, String name, String image, String description, List<ProductDto> products) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.products = products;
    }
}
