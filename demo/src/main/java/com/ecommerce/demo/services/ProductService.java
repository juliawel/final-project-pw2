package com.ecommerce.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.demo.domain.Product;
import com.ecommerce.demo.dtos.ProductDTO;
import com.ecommerce.demo.dtos.ProductMinDTO;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.exceptions.DatabaseException;
import com.ecommerce.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll(){

        var products = repository.findAll();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO getById(long id){

        var product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );

        return new ProductDTO(product);
    }

    @Transactional
    public ProductMinDTO insert(ProductMinDTO dto){

        var product = new Product();
        copyDtoToEntity(product, dto);
        repository.save(product);
        return new ProductMinDTO(product);
    }

    @Transactional
    public ProductMinDTO update(long id, ProductMinDTO dto) {

        try {
            var product = repository.getReferenceById(id);
            copyDtoToEntity(product, dto);
            repository.save(product);
            return new ProductMinDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Transactional
    public void delete(long id){

        if(!repository.existsById(id))
            throw new ResourceNotFoundException("Product not found");
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Relational integrity failure");
        }
    }

    private void copyDtoToEntity(Product entity, ProductMinDTO dto){
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgURL(dto.getImgUrl());
        entity.getCategories().clear();
    }
}
