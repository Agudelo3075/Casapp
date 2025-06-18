package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.Admin;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateAdminUseCase {
    private final EncripterPasswordUseCase encripterPasswordUseCase;
    private final LoggingService loggingService;

    public Admin updateAdmin(Admin adminDb, Admin adminEntrante){
        loggingService.logInfo("Inicializando actualización de administrador",adminDb.getId() ,adminDb.getEmail());
        adminDb.setId(adminDb.getId());
        adminDb.setName(adminEntrante.getName());
        adminDb.setLastName(adminEntrante.getLastName());
        adminDb.setEmail(adminEntrante.getEmail());
        adminDb.setPassword(encripterPasswordUseCase.encripter(adminEntrante.getPassword(), adminEntrante.getEmail()));
        adminDb.setPhone(adminEntrante.getPhone());
        adminDb.setRol(adminEntrante.getRol());
        adminDb.setArea(adminEntrante.getArea());
        loggingService.logDebug("Usuario actualizado exitosamente -ID: {}, Email: {}", adminDb.getId(), adminDb.getEmail());
        return adminDb;
    }
}
