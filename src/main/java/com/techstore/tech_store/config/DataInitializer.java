package com.techstore.tech_store.config;

import com.techstore.tech_store.entity.Role;
import com.techstore.tech_store.entity.User;
import com.techstore.tech_store.entity.UserStatus;
import com.techstore.tech_store.repository.RoleRepository;
import com.techstore.tech_store.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // 1. Khởi tạo ROLE_CUSTOMER
        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_CUSTOMER")));

        // 2. Khởi tạo ROLE_ADMIN
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        System.out.println(">> Roles initialized: ROLE_CUSTOMER, ROLE_ADMIN");

        // 3. Khởi tạo tài khoản Admin mặc định
        if (userRepository.findByEmail("admin@techstore.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@techstore.com");
            admin.setFullName("System Admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Mã hóa mật khẩu
            admin.setStatus(UserStatus.ACTIVE);
            admin.getRoles().add(adminRole);
            admin.getRoles().add(customerRole);
            userRepository.save(admin);
            System.out.println(">> Seeded Admin User: admin@techstore.com / admin123");
        }
    }
}