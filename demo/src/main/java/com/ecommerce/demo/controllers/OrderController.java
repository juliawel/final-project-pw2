package com.ecommerce.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.demo.dtos.OrderDTO;
import com.ecommerce.demo.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> dto = service.getAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable String id) {
        OrderDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
