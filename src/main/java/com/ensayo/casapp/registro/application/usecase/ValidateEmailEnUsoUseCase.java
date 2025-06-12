package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.exception.UserAlreadyRegisteredException;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateEmailEnUsoUseCase {
    private final IRepositoryPort repositoryPort;

    public void  validateEmail(String email){
        if(repositoryPort.findByEmail(email).isPresent()){
            throw new UserAlreadyRegisteredException(email);
        }
    }

}
