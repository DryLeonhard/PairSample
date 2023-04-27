package org.ssafy.sample.exception;

public class NotValidUserException extends Exception{
    public NotValidUserException(){
        super();
    }
    public NotValidUserException(String message){
        super(message);
    }
}