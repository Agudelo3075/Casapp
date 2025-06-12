package com.ensayo.casapp.registro.application.service;

import java.util.List;
import java.util.Optional;


import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.domain.port.in.IDelete;
import com.ensayo.casapp.registro.domain.port.in.IFindByFilters;
import com.ensayo.casapp.registro.domain.port.in.IFindById;
import com.ensayo.casapp.registro.domain.port.in.ISave;
import com.ensayo.casapp.registro.domain.port.in.IUpdate;

import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
public class RegisterService implements IDelete,IFindByFilters,ISave, IFindById, IUpdate{

    private final IDelete delete;
    private final IFindByFilters findByFilters;
    private final ISave save;
    private final IFindById findById;
    private final IUpdate update;

    @Override
    public Optional<User> delete(Long id) {
        return delete.delete(id);
    }

    @Override
    public List<User> findByFilters(String name, String lasname, String rol) {
        return findByFilters.findByFilters(name, lasname, rol);
    }

    @Override
    public User createUser(User user) {
        return save.createUser(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return findById.getById(id);
    }

    @Override
    public Optional<User> update(Long id, User user) {
        return update.update(id, user);
    }

}
