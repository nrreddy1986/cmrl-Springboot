package com.shellinfo.demo.service;

import com.shellinfo.demo.model.entity.MainCategory;
import com.shellinfo.demo.model.entity.Product;
import com.shellinfo.demo.model.entity.SubCategory;
import com.shellinfo.demo.repository.MainCategoryRepository;
import com.shellinfo.demo.repository.ProductRepository;
import com.shellinfo.demo.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private MainCategoryRepository mainRepo;

    @Autowired
    private SubCategoryRepository subRepo;

    @Autowired
    private ProductRepository productRepo;

    /// 🔹 Main Categories
    public List<MainCategory> getMainCategories() {
        return mainRepo.findAll();
    }

    /// 🔹 Sub Categories
    public List<SubCategory> getSubCategories(Long mainId) {
        return subRepo.findByMainCategoryId(mainId);
    }

    /// 🔹 Products
    public List<Product> getProducts(Long subId) {
        return productRepo.findBySubCategoryId(subId);
    }

    /// 🔹 Add APIs
    public MainCategory addMainCategory(MainCategory category) {
        return mainRepo.save(category);
    }

    public List<MainCategory> addMainCategories(List<MainCategory> categories) {
        return mainRepo.saveAll(categories);
    }

    public SubCategory addSubCategory(SubCategory c) {
        return subRepo.save(c);
    }

    public List<SubCategory> addSubCategories(List<SubCategory> categories) {
        return subRepo.saveAll(categories);
    }

    public Product addProduct(Product p) {
        return productRepo.save(p);
    }

    public List<Product> addProducts(List<Product> products) {
        return productRepo.saveAll(products);
    }
}