package com.techstore.tech_store.service.impl;

import com.techstore.tech_store.entity.Role;
import com.techstore.tech_store.entity.User;
import com.techstore.tech_store.entity.UserStatus;
import com.techstore.tech_store.repository.RoleRepository;
import com.techstore.tech_store.repository.UserRepository;
import com.techstore.tech_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String email, String password, String fullName) {
        //Checking existed email
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email Already Exists");
        }

        //Create new user
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFullName(fullName);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setStatus(UserStatus.ACTIVE);

        //Assign role CUSTOMER default
        Optional<Role> customerRole = roleRepository.findByName("ROLE_CUSTOMER");
        if(customerRole.isPresent()){
            newUser.getRoles().add(customerRole.get());
        }
        return  userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean validatePassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            return false;
        }
        return passwordEncoder.matches(password, user.get().getPassword());
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Invalid Password");
        }

        if(user.getStatus().equals(UserStatus.INACTIVE)){
            throw new IllegalArgumentException("Your account has been locked or inactive.");
        }
        return user;
    }
}
