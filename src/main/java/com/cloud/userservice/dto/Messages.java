package com.cloud.userservice.dto;

import com.cloud.userservice.model.user.Message;
import com.cloud.userservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    User user;
    List<Message> messages;
}
