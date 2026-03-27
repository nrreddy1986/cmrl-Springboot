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
    @PostMapping("/main-categories")
    public MainCategory addMain(@RequestBody MainCategory c) {
        return service.addMainCategory(c);
    }

    @PostMapping("/sub-categories")
    public SubCategory addSub(@RequestBody SubCategory c) {
        return service.addSubCategory(c);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product p) {
        return service.addProduct(p);
    }
}