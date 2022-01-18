package com.study.restfulwebservice.user;

// HTTP Status code
// 2xx -> ok
// 4xx -> client
// 5xx -> server

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
