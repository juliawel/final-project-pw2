package com.ecommerce.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.demo.domain.Category;
import com.ecommerce.demo.dtos.CategoryDTO;
import com.ecommerce.demo.dtos.CategoryMinDTO;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.services.exceptions.DatabaseException;
import com.ecommerce.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAll(){

        var categories = repository.findAll();

        return categories.stream().map(CategoryDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoryDTO getById(long id){

        var category = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Category not found")
        );

        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryMinDTO insert(CategoryMinDTO dto){
        var category = new Category();
        copyDtoToEntity(category, dto);
        repository.save(category);
        return new CategoryMinDTO(category);
    }

    @Transactional
    public CategoryMinDTO update(long id, CategoryMinDTO dto){
        try{
            var category = repository.getReferenceById(id);
            copyDtoToEntity(category, dto);
            repository.save(category);
            return new CategoryMinDTO(category);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Category not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(long id){
        if(!repository.existsById(id))
            throw new ResourceNotFoundException("Category not found");
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Relational integrity failure");
        }
    }

    private void copyDtoToEntity(Category entity, CategoryMinDTO dto){
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.getProducts().clear();
    }
}
