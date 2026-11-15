package com.techstore.tech_store.repository;

import com.techstore.tech_store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    /**
     * Find Role by name
     * @param name name of role(ex: ROLE_CUSTOMER, ROLE_ADMIN)
     * @return Optional have Role if it found
     */
    Optional<Role> findByName(String name);

}
