package com.shellinfo.demo.model.dto;

public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private String unit;
    private String image;
    private String description;

    public ProductDto(Long id, String name, double price, String unit, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.image = image;
        this.description = description;
    }
}
