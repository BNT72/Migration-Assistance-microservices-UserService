package com.cloud.userservice.controller;

import com.cloud.userservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange exchange) {
        return authService.login(exchange);
    }

    @PostMapping("/register")
    public Mono<ResponseEntity> register(ServerWebExchange exchange) {
        return authService.register(exchange);
    }
}
