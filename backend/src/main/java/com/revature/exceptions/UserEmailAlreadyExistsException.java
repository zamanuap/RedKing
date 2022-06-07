package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Email Already Exists")
public class UserEmailAlreadyExistsException extends Exception {
    public UserEmailAlreadyExistsException() { super("Email Already Exists"); }
}