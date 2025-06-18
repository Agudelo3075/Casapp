package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.Admin;
import com.ensayo.casapp.registro.domain.models.Customer;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentifyUserUpdateUseCase {
    private final UpdateAdminUseCase updateAdminUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final LoggingService loggingService;

    public User updateUserByType(User userDb, User userEntrante){
        loggingService.logInfo("Identificando tipo de usuario para la actualización - Email: {}", userDb.getEmail());
        if (userDb instanceof Customer && userEntrante instanceof Customer) {
            loggingService.logInfo("Actualizando usuario tipo customer -Email: {}", userDb.getEmail());
            return updateCustomerUseCase.updateCustomer((Customer) userDb, (Customer) userEntrante);
        }else if(userDb instanceof Admin && userEntrante instanceof Admin) {
            loggingService.logInfo("Actualizando usuario tipo admin -Email {}", userDb.getEmail());
            return updateAdminUseCase.updateAdmin((Admin) userDb, (Admin) userEntrante);
        } else{
            loggingService.logError("Error: Tipo de usuario no coincide - Email: {} ", userDb.getEmail());
            throw new IllegalArgumentException("Tipo de usuario no coincide");
        }
    }
}
