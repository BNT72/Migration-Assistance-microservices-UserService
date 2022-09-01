package com.cloud.userservice.service;

import com.cloud.userservice.model.user.User;
import com.cloud.userservice.repo.UserRepo;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements ReactiveUserDetailsService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepo.findByUsername(username).cast(UserDetails.class);
    }


    public Mono<UserDetails> saveUser(User user) {
        return userRepo.save(user).cast(UserDetails.class);
    }
}
