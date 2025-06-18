package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;

import com.ensayo.casapp.registro.application.exceptions.UserNotFoundException;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidationExistUseCase {
    private final IRepositoryPort repositoryPort;
    private final LoggingService loggingService;

    public void validateExist(Long id){
        loggingService.logInfo("Validando existencia de usuario con ID: {}", id);
        if (!repositoryPort.getById(id).isPresent()) {
            loggingService.logError("Usuario no encontrado con ID: {}", id);
            throw new UserNotFoundException(id);
        }
        loggingService.logDebug("Usuario encontrado con ID: {}", id);
    }
}
