package com.ensayo.casapp.registro.domain.port.out;

import java.util.List;
import java.util.Optional;

//import org.springframework.stereotype.Repository;

import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;

//@Repository
public interface IRepositoryPort {
    Optional<UserEntity> delete(Long id);
    List<UserEntity> findByFilters(String name, String lastName, String rol);
    Optional<UserEntity> getById(Long id);
    UserEntity create(UserEntity user);
    Optional<UserEntity> update(Long id, UserEntity user);
    Optional<UserEntity> findByEmail(String email);
}
