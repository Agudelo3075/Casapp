package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.domain.port.in.ISave;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;
import com.ensayo.casapp.registro.infraestructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveUseCase implements ISave{
    private final IRepositoryPort repositoryPort;
    private final UserMapper userMapper;
    private final ValidateEmailEnUsoUseCase validateEmailEnUsoUseCase;
    private final EncripterPasswordUseCase encripterPasswordUseCase;
    private final LoggingService loggingService;

    @Override
    public User createUser(User user) {
        loggingService.logInfo("Iniciando creación de usuario con email", user.getEmail());
        validateEmailEnUsoUseCase.validateEmail(user.getEmail());
        user.setPassword(encripterPasswordUseCase.encripter(user.getPassword(), user.getEmail()));

        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity userSaved = repositoryPort.create(userEntity);
        loggingService.logDebug("Usuario creado exitosamente -ID: {}, Email: {}", userSaved.getId(), userSaved.getEmail());
        return userMapper.toDomain(userSaved);
    } 
}
