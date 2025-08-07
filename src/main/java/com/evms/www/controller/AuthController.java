package com.evms.www.controller;

import com.evms.www.dto.LoginDto;
import com.evms.www.dto.RegisterDto;
import com.evms.www.model.User;
import com.evms.www.service.AuthService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evms/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser (@RequestBody RegisterDto registerDto) {
        return authService.registerUser(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser (@RequestBody LoginDto loginDto) {
        return authService.loginUser(loginDto);
    }
}
