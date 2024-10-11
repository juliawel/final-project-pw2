package com.ecommerce.demo.services;

import com.ecommerce.demo.dtos.CategoryDTO;
import com.ecommerce.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> getAll(){

        var categories = repository.findAll();

        return categories.stream().map(CategoryDTO::new).toList();
    }
}
