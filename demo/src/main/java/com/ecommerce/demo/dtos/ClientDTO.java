package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.User;

public class ClientDTO {

    private String id;
    private String name;

    public ClientDTO() {
    }

    public ClientDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDTO(User entity){
        id = entity.getId();
        name = entity.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
