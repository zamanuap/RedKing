package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Player Has Not Started a Game")
public class NoDeckInPlay extends Exception {
    public NoDeckInPlay() { super("Player Has Not Started a Game"); }
}