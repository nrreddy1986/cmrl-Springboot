package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {}