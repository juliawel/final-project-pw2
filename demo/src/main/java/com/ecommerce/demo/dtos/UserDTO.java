package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserDTO {

    private String id;

    @NotBlank
    @Size(min = 5, max = 150, message = "Username deve ter entre 5 e 150 caracteres")
    private String username;

    @NotBlank
    @Size(min = 5, max = 150, message = "Username deve ter entre 5 e 150 caracteres")
    private String name;

    @Email
    private String email;

    @Size(min = 9, max = 15, message = "Telefone deve ter entre 9 e 15 caracteres")
    private String phone;
    private LocalDate birthDate;
    @NotBlank
    @Size(min = 8, message = "Senha deve ter plo menos 8 caracteres")
    private String password;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String id, String username, String name, String email, String phone, LocalDate birthDate, String password, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.password = password;
        this.role = role;
    }

    public UserDTO(User entity){
        id = entity.getId();
        username = entity.getUsername();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        password = entity.getPassword();
        role = entity.getRoles().getRole();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
