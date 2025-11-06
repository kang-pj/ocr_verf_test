package com.refine.security;

import org.springframework.security.authentication.BadCredentialsException;

public class CustomBadCredentialsException extends BadCredentialsException {

    public CustomBadCredentialsException(String msg) {
        super(msg);
    }

    public CustomBadCredentialsException(String msg, Throwable t) {
        super(msg, t);
    }
}