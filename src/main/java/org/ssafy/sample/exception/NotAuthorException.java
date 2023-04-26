package org.ssafy.sample.exception;

public class NotAuthorException extends Exception{
    public NotAuthorException(){
        super();
    }
    public NotAuthorException(String message){
        super(message);
    }
}
