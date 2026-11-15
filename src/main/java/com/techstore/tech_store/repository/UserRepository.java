package com.techstore.tech_store.repository;

import com.techstore.tech_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Find User by email
     * @param email email of user
     * @return Optional have User if it found
     */

    Optional<User> findByEmail(String email);
}
