package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.exception.UserAlreadyRegisteredException;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateEmailEnUsoUseCase {
    private final IRepositoryPort repositoryPort;
    private final LoggingService loggingService;

    public void  validateEmail(String email){
        loggingService.logInfo("Validando email: {}", email);
        if(repositoryPort.findByEmail(email).isPresent()){
            loggingService.logError("Email ya registrado: {}", email);
            throw new UserAlreadyRegisteredException(email);
        }
        loggingService.logDebug("Email válido: {}", email);
    }

}
