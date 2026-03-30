package com.shellinfo.demo.model.dto;

import java.util.List;

public class HomeResponseDto {

    private Long id;
    private String name;
    private String image;
    private String description;
    private List<SubCategoryDto> subCategories;

    public HomeResponseDto(Long id, String name, String image,String description, List<SubCategoryDto> subCategories) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.subCategories = subCategories;
    }
}
