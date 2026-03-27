package com.shellinfo.demo.model.dto;

public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private String unit;
    private String image;

    public ProductDto(Long id, String name, double price, String unit, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.image = image;
    }
}
