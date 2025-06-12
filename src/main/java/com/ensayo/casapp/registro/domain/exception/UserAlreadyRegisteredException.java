package com.ensayo.casapp.registro.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyRegisteredException extends DomainException{
    private String email;

    public UserAlreadyRegisteredException(String email){
        super("El usuario con email " + email + "ya está registrado");
        this.email = email;
    }
}
