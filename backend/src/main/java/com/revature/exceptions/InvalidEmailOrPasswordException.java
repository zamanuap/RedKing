package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Email or Password is Incorrect")
public class InvalidEmailOrPasswordException extends Exception {
    public InvalidEmailOrPasswordException() { super("Could not find account with the given information"); }
}