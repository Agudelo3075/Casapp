package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.domain.port.in.ISave;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;
import com.ensayo.casapp.registro.infraestructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveUseCase implements ISave{
    private final IRepositoryPort repositoryPort;
    private final UserMapper userMapper;
    private final ValidateEmailEnUsoUseCase validateEmailEnUsoUseCase;
    private final EncripterPasswordUseCase encripterPasswordUseCase;


    @Override
    public User createUser(User user) {
        validateEmailEnUsoUseCase.validateEmail(user.getEmail());
        encripterPasswordUseCase.encripter(user.getPassword(), user.getEmail());

        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity userSaved = repositoryPort.create(userEntity);
        return userMapper.toDomain(userSaved);
    } 
}
