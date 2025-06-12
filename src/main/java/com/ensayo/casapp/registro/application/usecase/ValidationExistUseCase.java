package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;

import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidationExistUseCase {
    private final IRepositoryPort repositoryPort;

    public void validateExist(Long id){
        if (repositoryPort.getById(id).isPresent()) {
            
        }
    }
}
