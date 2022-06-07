package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Insufficient Funds For Withdrawal")
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException() { super("Insufficient Funds"); }    
}