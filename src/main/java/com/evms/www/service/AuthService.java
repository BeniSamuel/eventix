package com.evms.www.service;

import com.evms.www.dto.LoginDto;
import com.evms.www.dto.RegisterDto;
import com.evms.www.model.User;
import com.evms.www.utils.ApiResponse;
import com.evms.www.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public ResponseEntity<ApiResponse<User>> registerUser (RegisterDto registerDto) {
        User user = userService.createUser(registerDto);
        if (user != null) {
            return ApiResponse.created("Successfully registered user!!! 🎉🎉🎉", user);
        }
        return ApiResponse.badRequest("Failed to register a user bad request!!! 💔😔😔", null);
    }

    public ResponseEntity<ApiResponse<String>> loginUser (LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            User user = userService.getUserByEmail(loginDto.getEmail());
            return ApiResponse.ok("Successfully logged in a user!!! 🎉🎉🎉", jwtUtil.generateToken(user.getEmail()));
        } catch (AuthenticationException e) {
            return ApiResponse.badRequest("Failed to log in a user bad request!!! 💔😔😔", null);
        }
    }
}
