package com.reiuy.exception;

public class MyUserException extends Exception {
    //此处是检查类异常,所以继承exception而不是runtimeexception
    //runtimeexception是用来被运行时异常继承的
    public MyUserException() {
        super();
    }

    public MyUserException(String message) {
        super(message);
    }
}
