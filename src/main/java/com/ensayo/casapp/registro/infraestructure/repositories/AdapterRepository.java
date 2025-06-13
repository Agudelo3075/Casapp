package com.ensayo.casapp.registro.infraestructure.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdapterRepository implements IRepositoryPort{
    
    @Autowired
    private ORM orm;
    
    @Override
    public Optional<UserEntity> delete(Long id) {
        Optional<UserEntity> userEntity = orm.findById(id);
        if (userEntity.isPresent()) {
            orm.delete(userEntity.get());
            return userEntity;
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findByFilters(String name, String lastName, String rol) {
        List<UserEntity> users = orm.findByFilters(name, lastName, rol);
        return users;
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        Optional<UserEntity> userEntity = orm.findById(id);
        return userEntity;
    }

    @Override
    public UserEntity create(UserEntity user) {
        UserEntity userEntity = orm.save(user);
        return userEntity;
    }

    @Override
    public Optional<UserEntity> update(Long id, UserEntity user) {
        Optional<UserEntity> userCreated = orm.findById(id);
        if (userCreated.isPresent()) {
            UserEntity existingUser = userCreated.get();
            user.setId(existingUser.getId());
            UserEntity userUpdate = orm.save(user);
            return Optional.of(userUpdate);
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Optional<UserEntity> userEntity = orm.findByEmail(email);
        return userEntity;
    }

}
