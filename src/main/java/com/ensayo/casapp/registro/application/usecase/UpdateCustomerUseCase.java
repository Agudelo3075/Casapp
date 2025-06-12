package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {
    private EncripterPasswordUseCase encripterPasswordUseCase;

    public Customer updateCustomer(Customer customerDb, Customer customerEntrante){
        customerDb.setId(customerDb.getId());
        customerDb.setName(customerEntrante.getName());
        customerDb.setLastName(customerEntrante.getLastName());
        customerDb.setEmail(customerEntrante.getEmail());
        customerDb.setPassword(encripterPasswordUseCase.encripter(customerEntrante.getPassword(), customerEntrante.getEmail()));
        customerDb.setPhone(customerEntrante.getPhone());
        customerDb.setRol(customerDb.getRol());
        customerDb.setBirthDate(customerEntrante.getBirthDate());
        return customerDb;
    }
}
