package com.cloud.userservice.service;

import com.cloud.userservice.config.secure.JwtUtil;
import com.cloud.userservice.model.user.User;
import com.cloud.userservice.model.user.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    public Mono<ResponseEntity> login(ServerWebExchange exchange) {
        return exchange.getFormData()
                .flatMap(stringStringMultiValueMap -> userService.
                        findByUsername(stringStringMultiValueMap.getFirst("username"))
                        .cast(User.class)
                        .map(user -> Objects.equals(
                                        stringStringMultiValueMap.getFirst("password"),
                                        user.getPassword()
                                )
                                        ? ResponseEntity.ok(jwtUtil.generateToken(user))
                                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
                        ).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
                );

    }

    public Mono<ResponseEntity> register(ServerWebExchange exchange) {
        return exchange.getFormData()
                .flatMap(stringStringMultiValueMap -> userService.
                        findByUsername(stringStringMultiValueMap.getFirst("username"))
                        .cast(User.class)
                        .map(user -> Objects.equals(null, user)
                                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                                : ResponseEntity.status(HttpStatus.CONFLICT).build()
                        ).defaultIfEmpty(ResponseEntity.ok(
                                userService.saveUser(
                                        User.builder()
                                                .username(stringStringMultiValueMap.getFirst("username"))
                                                .password(stringStringMultiValueMap.getFirst("password"))
                                                .role(UserRole.ROLE_USER)
                                                .build()))
                        ));
    }
}
