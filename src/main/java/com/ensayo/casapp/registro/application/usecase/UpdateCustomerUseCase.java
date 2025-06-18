package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.Customer;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {
    private final EncripterPasswordUseCase encripterPasswordUseCase;
    private final LoggingService loggingService;

    public Customer updateCustomer(Customer customerDb, Customer customerEntrante){
        loggingService.logInfo("Iniciando actualización de Customer -ID {}, Email: {}", customerDb.getId(), customerDb.getEmail());
        customerDb.setId(customerDb.getId());
        customerDb.setName(customerEntrante.getName());
        customerDb.setLastName(customerEntrante.getLastName());
        customerDb.setEmail(customerEntrante.getEmail());
        customerDb.setPassword(encripterPasswordUseCase.encripter(customerEntrante.getPassword(), customerEntrante.getEmail()));
        customerDb.setPhone(customerEntrante.getPhone());
        customerDb.setRol(customerDb.getRol());
        customerDb.setBirthDate(customerEntrante.getBirthDate());
        loggingService.logDebug("Customer actualizado exitosamente -ID: {}, Email: {}", customerDb.getId(), customerDb.getEmail());
        return customerDb;
    }
}
