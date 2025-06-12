package com.ensayo.casapp.registro.infraestructure.exceptions;

import com.ensayo.casapp.registro.application.exceptions.ApplicationException;

public class InfraestructureException extends RuntimeException{

    public InfraestructureException(String message){
        super(message);
    }

    public InfraestructureException(String message, Throwable cause){
        super(message, cause);
    }

    public InfraestructureException(ApplicationException applicationException){
        super(applicationException.getMessage(), applicationException);
    }
}
