package com.techstore.tech_store.service;

import com.techstore.tech_store.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    /**
     * * Sign up new user
     * @param email email of user
     * @param password password plaintext (will be hash)
     * @param fullName full name
     * @return User entity lasted creation
     * @throws IllegalArgumentException if the email has existed
     */
    User registerUser(String email, String password, String fullName) throws Exception;

    /**
     * Find user by email
     * @param email email to look for
     * @return Optional have User if it found
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user by id
     * @param id id to look for
     * @return Optional have User if it found
     */
    Optional<User> findById(UUID id);

    /**
     *  Take all users
     * @return List all users
     */
    List<User> findAll();

    /**
     * Delete user
     * @param id UUID of user who need to delete
     */
    void deleteUser(UUID id);

    /**
     * Check password
     * @param email email of user
     * @param password password plaintext to check
     * @return true if password was right
     */
    boolean validatePassword(String email, String password);

    /**
     * Auth login information
     * @param email Email login
     * @param password Password login
     * @return User if right information
     */
    User login(String email, String password);
}
