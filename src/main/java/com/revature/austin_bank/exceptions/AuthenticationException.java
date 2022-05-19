package com.revature.austin_bank.exceptions;


public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}

