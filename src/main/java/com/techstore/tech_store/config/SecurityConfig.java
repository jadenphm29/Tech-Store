package com.techstore.tech_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /**
     * Tạo BCryptPasswordEncoder bean
     * Sử dụng để hash password khi register
     * Sử dụng để kiểm tra password khi login
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        // Tắt CSRF vì chúng ta đang dùng REST API
            .csrf(csrf -> csrf.disable())
                // Phân quyền request
                .authorizeHttpRequests(auth -> auth
                        // Cho phép gọi tự do các API liên quan đến User và Auth
                        .requestMatchers("/api/users/**", "/api/auth/**").permitAll()
                        // Các request tĩnh (ảnh, css, js) nếu sau này dùng Thymeleaf
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // Tất cả các request còn lại yêu cầu xác thực
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
