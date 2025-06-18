package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;

import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncripterPasswordUseCase {
    private final PasswordEncoder passwordEncoder;
    private final LoggingService loggingService;

    public String encripter(String password, String email){
        loggingService.logInfo("Encriptando contraseña para usuario con email {}", email);
        String passwordEncripted = passwordEncoder.encode(password);
        loggingService.logDebug("Contraseña encriptada exitosamente para email {}", email);
        return passwordEncripted; 
    }
}
