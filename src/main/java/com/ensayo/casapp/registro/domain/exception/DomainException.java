package com.ensayo.casapp.registro.domain.exception;

public class DomainException extends RuntimeException{

    public DomainException(String message){
        super(message);
    }

    public DomainException(String message, Throwable cause){
        super(message,cause);
    }
}
