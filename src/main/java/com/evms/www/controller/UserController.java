package com.evms.www.controller;

import com.evms.www.dto.RegisterDto;
import com.evms.www.model.User;
import com.evms.www.service.UserService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evms/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers () {
        return ApiResponse.ok("Successfully obtained all user!!! 🎉🎉🎉", this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById (@PathVariable Long id) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            return ApiResponse.ok("Successfully obtained a user!!! 🎉🎉🎉", user);
        }
        return ApiResponse.notFound("Failed to obtain user!!! 💔😔😔", null);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<User>> getUserByEmail (@PathVariable String email) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            return ApiResponse.ok("Successfully obtained a user!!! 🎉🎉🎉", user);
        }
        return ApiResponse.notFound("Failed to obtain user!!! 💔😔😔", null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<User>> updateUserById (@PathVariable Long id, @RequestBody RegisterDto registerDto) {
        User user = this.userService.updateUserById(id, registerDto);
        if (user != null) {
            return ApiResponse.ok("Successfully updated a user!!! 🎉🎉🎉", user);
        }
        return ApiResponse.notFound("Failed to update user not found!!! 💔😔😔", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUserById (@PathVariable Long id) {
        return this.userService.deleteUser(id) ?
                ApiResponse.ok("Successfully deleted a user!!! 🎉🎉🎉", true) :
                ApiResponse.notFound("Failed to delete a user not found!!! 💔😔😔", false);
    }
}
