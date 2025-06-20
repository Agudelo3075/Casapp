package com.ensayo.casapp.registro.application.usecase;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.domain.port.in.IUpdate;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;
import com.ensayo.casapp.registro.infraestructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUseCase implements IUpdate{
    private final IRepositoryPort repositoryPort;
    private final UserMapper userMapper;
    private final ValidationExistUseCase validationExistUseCase;
    private final IdentifyUserUpdateUseCase identifyUserUpdateUseCase;
    private final ValidateEmailEnUsoUseCase validateEmailEnUsoUseCase;
    private final LoggingService loggingService;

    @Override
    public Optional<User> update(Long id, User user) {
        loggingService.logInfo("Iniciando actualización de usuario - ID: {}, Email: {}", id, user.getEmail());
        validationExistUseCase.validateExist(id);
        Optional<UserEntity> userEntity = repositoryPort.getById(id);
        if (!user.getEmail().equals(userEntity.get().getEmail())) {
            loggingService.logDebug("Validando nuevo email: {}", user.getEmail());
            validateEmailEnUsoUseCase.validateEmail(user.getEmail());
        }

        User userReturn = identifyUserUpdateUseCase.updateUserByType(userMapper.toDomain(userEntity.get()), user);
        repositoryPort.update(id, userMapper.toEntity(userReturn));
        loggingService.logDebug("Usuario actualizado exitosamente - ID: {}, Email: {}", id, userReturn.getEmail());
        return Optional.of(userReturn);
    }

    
}
