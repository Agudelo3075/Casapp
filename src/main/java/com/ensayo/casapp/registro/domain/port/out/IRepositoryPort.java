package com.ensayo.casapp.registro.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;

public interface IRepositoryPort {
    Optional<UserEntity> delete(Long id);
    List<UserEntity> findByFilters(String name, String lastName, String rol);
    Optional<UserEntity> getBYId(Long id);
    UserEntity create(UserEntity user);
    Optional<UserEntity> update(Long id, UserEntity user);
    Optional<UserEntity> findByEmail(String email);
}
