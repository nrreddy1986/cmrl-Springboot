package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySubCategoryId(Long subCategoryId);

    /// 🔹 Limit products (Blinkit style)
    List<Product> findTop10BySubCategoryId(Long subCategoryId);
}