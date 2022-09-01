package com.cloud.userservice.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter

@Table(name = "message")
public class Message {
    @Id
    private Long id;

    private String message;

    private LocalDateTime dateTime;

    @JsonIgnore
    private Long userId;

    private String username;


}


