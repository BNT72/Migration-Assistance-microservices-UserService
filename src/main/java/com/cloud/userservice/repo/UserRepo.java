package com.cloud.userservice.repo;

import com.cloud.userservice.model.user.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveCrudRepository<User,Long> {
    Mono<User> findByUsername(String name);

    @Query("SELECT usr.id,usr.username,password,role from usr\n" +
            "    INNER JOIN   message m on usr.id = m.user_id\n" +
            "                                          group by usr.id, usr.username, password,role")
    Flux<User> findAllByMessagesIsNotNull();
}
