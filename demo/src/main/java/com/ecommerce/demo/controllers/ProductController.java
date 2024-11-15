package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dtos.ProductDTO;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductDTO> insert(@Valid @PathVariable ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    public ResponseEntity<ProductDTO> update() {

        return null;
    }

    public ResponseEntity<ProductDTO> delete() {

        return null;
    }

}
