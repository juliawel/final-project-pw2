package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dtos.CategoryDTO;
import com.ecommerce.demo.dtos.OrderDTO;
import com.ecommerce.demo.repositories.OrderRepository;
import com.ecommerce.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
class OrderController {

    @Autowired
    private OrderService service;

    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> dto = service.getAll();
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<OrderDTO> findById() {

        return null;
    }

    public ResponseEntity<OrderDTO> insert() {

        return null;
    }

}
