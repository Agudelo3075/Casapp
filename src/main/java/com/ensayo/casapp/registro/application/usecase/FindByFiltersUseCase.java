package com.ensayo.casapp.registro.application.usecase;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.domain.port.in.IFindByFilters;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindByFiltersUseCase implements IFindByFilters{
    private final IRepositoryPort repositoryPort;
    private final UserMapper userMapper;


    @Override
    public List<User> findByFilters(String name, String lasname, String rol) {
        List<User> users = repositoryPort.findByFilters(lasname, name, rol)
        .stream()
        .map(userMapper::toDomain)
        .collect(Collectors.toList());
        return users;
    }

    
}
