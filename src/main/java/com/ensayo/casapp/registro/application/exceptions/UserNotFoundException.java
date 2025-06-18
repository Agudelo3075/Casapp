package com.ensayo.casapp.registro.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;

import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND) //Pregunta... Qué hace esta linea?
public class UserNotFoundException extends RuntimeException{
    private final Long id;
    private final LoggingService loggingService;

    public UserNotFoundException(Long id){
        super("usuario no encontrado con el id:" + id);
        this.id = id;
        this.loggingService = new LoggingService();
        loggingService.logDebug("Usuario no encontrado con - ID:{}", id);
    }
}
