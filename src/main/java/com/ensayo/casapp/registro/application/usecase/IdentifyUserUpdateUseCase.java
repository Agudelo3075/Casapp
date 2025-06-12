package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.Admin;
import com.ensayo.casapp.registro.domain.models.Customer;
import com.ensayo.casapp.registro.domain.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentifyUserUpdateUseCase {
    private final UpdateAdminUseCase updateAdminUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public User updateUserByType(User userDb, User userEntrante){
        if (userDb instanceof Customer && userEntrante instanceof Customer) {
            return updateCustomerUseCase.updateCustomer((Customer) userDb, (Customer) userEntrante);
        }else if(userDb instanceof Admin && userEntrante instanceof Admin) {
            return updateAdminUseCase.updateAdmin((Admin) userDb, (Admin) userEntrante);
        } else{
            throw new IllegalArgumentException("Tipo de usuario no coincide");
        }
    }
}
