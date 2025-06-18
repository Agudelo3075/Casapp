package com.ensayo.casapp.registro.infraestructure.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdapterRepository implements IRepositoryPort{
    
    @Autowired
    private ORM orm;
    private final LoggingService loggingService;
    
    @Override
    public Optional<UserEntity> delete(Long id) {
        loggingService.logInfo("Eliminando usuario de la base de datos - ID: {}", id);
        Optional<UserEntity> userEntity = orm.findById(id);
        if (userEntity.isPresent()) {
            orm.delete(userEntity.get());
            loggingService.logDebug("Usuario eliminado de la base de datos - ID: {}", id);
            return userEntity;
        }
        loggingService.logWarning("Usuario no encontrado en la base de datos para eliminar - ID: {}", id);
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findByFilters(String name, String lastName, String rol) {
        loggingService.logInfo("Buscando usuarios en la base de datos con filtros - Nombre: {}, Apellido: {}, Rol: {}", name, lastName, rol);
        List<UserEntity> users = orm.findByFilters(name, lastName, rol);
        loggingService.logInfo("Se encontraron {} usuarios en la base de datos", users.size());
        return users;
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        loggingService.logInfo("Buscando usuario en la base de datos por ID: {}", id);
        Optional<UserEntity> user = orm.findById(id);
        if (user.isPresent()) {
            loggingService.logDebug("Usuario encontrado en la base de datos - ID: {}", id);
        } else {
            loggingService.logWarning("Usuario no encontrado en la base de datos - ID: {}", id);
        }
        return user;
    }

    @Override
    public UserEntity create(UserEntity user) {
        loggingService.logInfo("Creando nuevo usuario en la base de datos - Email: {}", user.getEmail());
        UserEntity userEntity = orm.save(user);
        loggingService.logInfo("Usuario creado en la base de datos - ID: {}, Email: {}", userEntity.getId(), userEntity.getEmail());
        return userEntity;
    }

    @Override
    public Optional<UserEntity> update(Long id, UserEntity user) {
        loggingService.logInfo("Actualizando usuario en la base de datos - ID: {}, Email: {}", id, user.getEmail());
        Optional<UserEntity> userValid = orm.findById(id);
        if(userValid.isPresent()){
            UserEntity existingUser = userValid.get();
            user.setId(existingUser.getId());
            UserEntity updatedUser = orm.save(user);
            loggingService.logDebug("Usuario actualizado en la base de datos - ID: {}, Email: {}", id, updatedUser.getEmail());
            return Optional.of(updatedUser);   
        }
        loggingService.logWarning("Usuario no encontrado en la base de datos para actualizar - ID: {}", id);
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        loggingService.logInfo("Buscando usuario en la base de datos por email: {}", email);
        Optional<UserEntity> user = orm.findByEmail(email);
        if (user.isPresent()) {
            loggingService.logDebug("Usuario encontrado en la base de datos - Email: {}", email);
        } else {
            loggingService.logWarning("Usuario no encontrado en la base de datos - Email: {}", email);
        }
        return user;
    }

}
