package com.ensayo.casapp.registro.domain.exception;

public class UserAlreadyRegisteredException extends DomainException{
    private String email;
    //private LoggingService LoggingService;

    public UserAlreadyRegisteredException(String email){
        super("El usuario con email " + email + "ya está registrado");
        this.email = email;
        //this.loggingService=new LoggingService();
        //loggingService.logDebug("Usuario ya registrado con - EMAIL: {}", email);
    }
}
