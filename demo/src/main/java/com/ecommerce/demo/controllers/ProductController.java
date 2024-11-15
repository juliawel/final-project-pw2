package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dtos.ProductDTO;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> dto = service.getAll();
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
        ProductDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
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
