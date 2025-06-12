package com.ensayo.casapp.registro.application.exceptions;

import com.ensayo.casapp.registro.domain.exception.DomainException;

public class ApplicationException extends RuntimeException{

    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(String message, Throwable cause){
        super(message, cause);
    }

    public ApplicationException(DomainException domainException){
        super(domainException.getMessage(), domainException);
    }
}
