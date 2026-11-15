package com.techstore.tech_store.controller.api;

import com.techstore.tech_store.dto.request.LoginRequest;
import com.techstore.tech_store.dto.response.AuthResponse;
import com.techstore.tech_store.dto.response.ErrorResponse;
import com.techstore.tech_store.entity.User;
import com.techstore.tech_store.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST /api/auth/login
     * Đăng nhập hệ thống
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            User user = userService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse("Login successful", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401
                    .body(new ErrorResponse(e.getMessage(), 401));
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) // 403 (tài khoản bị khóa)
                    .body(new ErrorResponse(e.getMessage(), 403));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body(new ErrorResponse("An error occurred: " + e.getMessage(), 500));
        }
    }
}