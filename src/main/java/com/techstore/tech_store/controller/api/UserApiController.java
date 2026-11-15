package com.techstore.tech_store.controller.api;

import com.techstore.tech_store.dto.request.UserRegistrationRequest;
import com.techstore.tech_store.dto.response.ErrorResponse;
import com.techstore.tech_store.dto.response.UserResponse;
import com.techstore.tech_store.entity.User;
import com.techstore.tech_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserApiController {

    @Autowired
    private UserService userService;

    /**
     * GET /api/user
     * List all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User>  users = userService.findAll();

        List<UserResponse> response = users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/users/{id}
     * Lấy user theo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", 404));
        }

        return ResponseEntity.ok(new UserResponse(user.get()));
    }

    /**
     * GET /api/users/email/{email}
     * Lấy user theo email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", 404));
        }

        return ResponseEntity.ok(new UserResponse(user.get()));
    }

    /**
     * POST /api/users
     * Tạo user mới (Register)
     */
    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        try {
            User newUser = userService.registerUser(
                    request.getEmail(),
                    request.getPassword(),
                    request.getFullName()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new UserResponse(newUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), 400));
        } catch (Exception e) {  //Catch tất cả exception khác
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An error occurred: " + e.getMessage(), 500));
        }
    }

    /**
     * DELETE /api/users/{id}
     * Xóa user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", 404));
        }

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();  // 204
    }
}
