package com.evms.www.controller;

import com.evms.www.dto.RegisterDto;
import com.evms.www.model.User;
import com.evms.www.service.UserService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(new ApiResponse<>(true, "Successfully obtained all user!!! 🎉🎉🎉", this.userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById (@PathVariable Long id) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Successfully obtained a user!!! 🎉🎉🎉", user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "Failed to obtain a user!!! 💔💔😔", null));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<User>> getUserByEmail (@PathVariable String email) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Successfully obtained a user!!! 🎉🎉🎉", user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "Failed to obtain a user!!! 💔💔😔", null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<User>> updateUserById (@PathVariable Long id, @RequestBody RegisterDto registerDto) {
        User user = this.userService.updateUserById(id, registerDto);
        if (user != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Successfully updated a user!!! 🎉🎉🎉", user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "Failed to update a user!!! 💔💔😔", null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUserById (@PathVariable Long id) {
        return this.userService.deleteUser(id) ?
                ResponseEntity.ok(new ApiResponse<>(true, "Successfully deleted a user!!! 🎉🎉🎉", true)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "Failed to deleted a user!!! 💔💔😔", false));
    }
}
