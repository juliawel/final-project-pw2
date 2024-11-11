package com.ecommerce.demo.repositories;

import com.ecommerce.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT obj FROM Roles obj WHERE obj.role = :roleName")
    Role searchByNomeRole(String roleName);
}
