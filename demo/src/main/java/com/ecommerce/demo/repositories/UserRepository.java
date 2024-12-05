package com.ecommerce.demo.repositories;

import com.ecommerce.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByUsername(String username);

    @Query(value = "SELECT * FROM users U WHERE U.username = :username", nativeQuery = true)
    User searchByUsername(String username);
}
