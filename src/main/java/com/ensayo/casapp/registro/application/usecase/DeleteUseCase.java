package com.ensayo.casapp.registro.application.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.domain.port.in.IDelete;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;
import com.ensayo.casapp.registro.infraestructure.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteUseCase implements IDelete{
    private final IRepositoryPort repositoryPort;
    private final ValidationExistUseCase validationExistUseCase;
    private final UserMapper userMapper;
    private final LoggingService loggingService;

    @Override
    public Optional<User> delete(Long id) {
        loggingService.logInfo("Iniciando proceso de eliminación para el usuario con - ID: {}", id);
        validationExistUseCase.validateExist(id);
        Optional<UserEntity> userEntity = repositoryPort.getById(id);
        repositoryPort.delete(userEntity.get().getId());
        loggingService.logDebug("Usuario eliminado exitosamente - ID: {}, Email: {}", id, userEntity.get().getEmail());
        return Optional.of(userMapper.toDomain(userEntity.get()));
    }

    
}
