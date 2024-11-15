package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dtos.ProductDTO;
import com.ecommerce.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    public ResponseEntity<List<ProductDTO>> findAll() {

        return null;
    }

    public ResponseEntity<ProductDTO> findById() {

        return null;
    }

    public ResponseEntity<ProductDTO> insert() {

        return null;
    }

    public ResponseEntity<ProductDTO> update() {

        return null;
    }

    public ResponseEntity<ProductDTO> delete() {

        return null;
    }

}
