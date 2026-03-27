package com.shellinfo.demo.model.dto;

import java.util.List;

public class HomeResponseDto {

    private Long id;
    private String name;
    private String image;
    private List<SubCategoryDto> subCategories;

    public HomeResponseDto(Long id, String name, String image, List<SubCategoryDto> subCategories) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.subCategories = subCategories;
    }
}
