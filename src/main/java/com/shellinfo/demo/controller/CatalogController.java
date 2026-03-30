package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.entity.MainCategory;
import com.shellinfo.demo.model.entity.Product;
import com.shellinfo.demo.model.entity.SubCategory;
import com.shellinfo.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogService service;

    /// 🔹 Main Categories
    @GetMapping("/main-categories")
    public List<MainCategory> getMainCategories() {
        return service.getMainCategories();
    }

    /// 🔹 Sub Categories
    @GetMapping("/sub-categories")
    public List<SubCategory> getSubCategories(@RequestParam Long mainId) {
        return service.getSubCategories(mainId);
    }

    /// 🔹 Products
    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam Long subId) {
        return service.getProducts(subId);
    }

    /// 🔹 Add APIs
    @PostMapping("/main-category")
    public MainCategory addMainCategory(@RequestBody MainCategory category) {
        return service.addMainCategory(category);
    }

    /// 🔹 Add APIs
    @PostMapping("/main-categories")
    public List<MainCategory> addMainCategories(
            @RequestBody List<MainCategory> categories) {

        return service.addMainCategories(categories);
    }

    @PostMapping("/sub-category")
    public SubCategory addSubCategory(@RequestBody SubCategory category) {
        return service.addSubCategory(category);
    }

    @PostMapping("/sub-categories")
    public List<SubCategory> addSubCategories(@RequestBody List<SubCategory> subCategories) {
        return service.addSubCategories(subCategories);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product p) {
        return service.addProduct(p);
    }

    @PostMapping("/products")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.addProducts(products);
    }
}