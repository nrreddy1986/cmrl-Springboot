package com.shellinfo.demo.model.dto;

import java.util.List;

public class SubCategoryDto {

    private Long id;
    private String name;
    private String image;
    private List<ProductDto> products;

    public SubCategoryDto(Long id, String name, String image, List<ProductDto> products) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.products = products;
    }
}
