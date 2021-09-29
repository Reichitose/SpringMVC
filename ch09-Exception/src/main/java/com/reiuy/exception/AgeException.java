package com.reiuy.exception;


//当年龄出现问题时抛出的异常
public class AgeException extends MyUserException {
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}
