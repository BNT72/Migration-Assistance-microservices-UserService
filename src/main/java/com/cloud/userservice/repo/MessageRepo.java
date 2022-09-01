package com.cloud.userservice.repo;

import com.cloud.userservice.model.user.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepo extends ReactiveCrudRepository<Message,Long> {
    Flux<Message> findAllByUserIdOrderByDateTime(Long user_id);
}
