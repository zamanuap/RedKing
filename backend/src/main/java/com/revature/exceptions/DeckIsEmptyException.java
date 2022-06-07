package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No Cards Left in the Deck")
public class DeckIsEmptyException extends Exception {
    public DeckIsEmptyException() { super("No Cards Left to Draw"); }
}