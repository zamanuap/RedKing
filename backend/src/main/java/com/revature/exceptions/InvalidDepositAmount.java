package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Deposit Amount Less Than 0")
public class InvalidDepositAmount extends Exception {
    public InvalidDepositAmount() { super("Invalid Deposit Amount"); }
}
