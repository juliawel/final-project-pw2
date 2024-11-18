package com.ecommerce.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.domain.User;
import com.ecommerce.demo.dtos.LoginDTO;
import com.ecommerce.demo.dtos.TokenDTO;
import com.ecommerce.demo.security.TokenService;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private TokenService service;

    @Autowired
    private AuthenticationManager manager;

    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity login(@RequestBody LoginDTO login) {
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJwt = service.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJwt));
    }
}
