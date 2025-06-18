package com.ensayo.casapp.registro.application.usecase;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.domain.port.in.IFindById;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;
import com.ensayo.casapp.registro.infraestructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindByIdUseCase implements IFindById{
    private final IRepositoryPort repositoryPort;
    private final UserMapper userMapper;
    private final ValidationExistUseCase validationExistUseCase;
    private final LoggingService loggingService;

    @Override
    public Optional<User> getById(Long id) {
        loggingService.logInfo("Buscando el usuario con -ID: {}", id);
        validationExistUseCase.validateExist(id);
        User user = userMapper.toDomain(repositoryPort.getById(id).get());
        loggingService.logInfo("Usuario encontrado con ID: {}, Email: {} ", id, user.getEmail());
        return Optional.of(user);
    }
}
