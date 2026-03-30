package com.shellinfo.demo.service;

import com.shellinfo.demo.model.dto.HomeResponseDto;
import com.shellinfo.demo.model.dto.ProductDto;
import com.shellinfo.demo.model.dto.SubCategoryDto;
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
public class HomeService {

    @Autowired
    private MainCategoryRepository mainRepo;

    @Autowired
    private SubCategoryRepository subRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<HomeResponseDto> getHomeData() {

        List<MainCategory> mains = mainRepo.findAll();

        return mains.stream().map(main -> {

            List<SubCategory> subs = subRepo.findByMainCategoryId(main.getId());

            List<SubCategoryDto> subDtos = subs.stream().map(sub -> {

                List<Product> products =
                        productRepo.findTop10BySubCategoryId(sub.getId());

                List<ProductDto> productDtos = products.stream()
                        .map(p -> new ProductDto(
                                p.getId(),
                                p.getName(),
                                p.getPrice(),
                                p.getUnit(),
                                p.getImage(),
                                p.getDescription()
                        ))
                        .toList();

                return new SubCategoryDto(
                        sub.getId(),
                        sub.getName(),
                        sub.getImage(),
                        sub.getDescription(),
                        productDtos
                );

            }).toList();

            return new HomeResponseDto(
                    main.getId(),
                    main.getName(),
                    main.getImage(),
                    main.getDescription(),
                    subDtos
            );

        }).toList();
    }
}