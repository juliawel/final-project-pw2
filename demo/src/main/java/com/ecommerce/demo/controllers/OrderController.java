package com.ecommerce.demo.controllers;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.demo.dtos.OrderDTO;
import com.ecommerce.demo.dtos.ResponseMessage;
import com.ecommerce.demo.security.TokenService;
import com.ecommerce.demo.services.OrderService;
import com.ecommerce.demo.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
@SecurityRequirement(name = "bearer-key")
class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

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
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto,
            @RequestHeader("Authorization") String token) {

        var username = tokenService.getSubject(token);
        var user = userService.findByUsername(username);
        dto = service.insert(dto, user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/cancelOrder/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable String id) {
        
        var cancelOrder = service.cancelOrder(id);

        if (cancelOrder.statusCode() == 400) {
            return ResponseEntity.badRequest().body(cancelOrder.message());
        }

        return ResponseEntity.ok().body(cancelOrder.message());
    }
}
