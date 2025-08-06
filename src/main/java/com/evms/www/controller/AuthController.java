package com.evms.www.controller;

import com.evms.www.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evms/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;


}
