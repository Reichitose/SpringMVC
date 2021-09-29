package com.reiuy.exception;


//表示用户的name有异常时,抛出的NameException
public class NameException extends MyUserException {
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
