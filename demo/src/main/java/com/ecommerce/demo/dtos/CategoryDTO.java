package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {

    private Long id;
    private String name;
    private List<ProductDTO> products = new ArrayList<>();

    public CategoryDTO(Long id, String name, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
        entity.getProducts().forEach(prod -> this.products.add(new ProductDTO(prod)));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
}
