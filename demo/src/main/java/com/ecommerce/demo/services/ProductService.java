package com.ecommerce.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.demo.domain.Category;
import com.ecommerce.demo.domain.Product;
import com.ecommerce.demo.dtos.InsertProductCategory;
import com.ecommerce.demo.dtos.ProductDTO;
import com.ecommerce.demo.dtos.ProductMinDTO;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.exceptions.DatabaseException;
import com.ecommerce.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll(){

        var products = productRepository.findAll();

        return products.stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO getById(long id){

        var product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );

        return new ProductDTO(product);
    }

    @Transactional
    public ProductMinDTO insert(ProductMinDTO dto) {

        var product = new Product();
        copyDtoToEntity(product, dto);
        productRepository.save(product);
        return new ProductMinDTO(product);
    }
    
    @Transactional
    public ProductDTO insertProductIntoCategory(InsertProductCategory productCategory) {
        
        Product product = productRepository.findById(productCategory.productId()).orElseThrow(
            () -> new ResourceNotFoundException("Product not found")
        );
        Category category = categoryRepository.findById(productCategory.categoryId()).orElseThrow(
            () -> new ResourceNotFoundException("Category not found")
        );

        category.getProducts().add(product);
        categoryRepository.save(category);

        product.getCategories().add(category);

        return new ProductDTO(product);
    }

    @Transactional
    public ProductMinDTO update(long id, ProductMinDTO dto) {

        try {
            var product = productRepository.getReferenceById(id);
            copyDtoToEntity(product, dto);
            productRepository.save(product);
            return new ProductMinDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Transactional
    public void delete(long id){

        if(!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product not found");
        try {
            productRepository.deleteById(id);
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
