package com.ecommerce.demo.services;

import com.ecommerce.demo.domain.Role;
import com.ecommerce.demo.domain.User;
import com.ecommerce.demo.dtos.UserDTO;
import com.ecommerce.demo.repositories.RoleRepository;
import com.ecommerce.demo.repositories.UserRepository;
import com.ecommerce.demo.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserDTO insert(UserDTO dto) {
        Role role = roleRepository.searchByNomeRole(dto.getRole());
        User user = new User();
        copyDtoToEntity(dto, user);
        user.setRoles(role);
        var senhaCriptografada = new BCryptPasswordEncoder().encode(dto.getPassword());
        user.setPassword(senhaCriptografada);
        userRepository.save(user);
        return new UserDTO(user);
    }
    
    @Transactional(readOnly = true)
    public UserDTO findByUsername(String username){

        var user = userRepository.searchByUsername(username);
        if(user == null)
            throw new ResourceNotFoundException("User not found");

        return new UserDTO(user);
    }

    private void copyDtoToEntity(UserDTO dto, User entity){
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
    }
}
