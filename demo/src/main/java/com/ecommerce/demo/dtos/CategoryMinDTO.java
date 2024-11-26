package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.Category;

public class CategoryMinDTO {

    private Long id;
    private String name;

    public CategoryMinDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryMinDTO() {
    }

    public CategoryMinDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    
}
