package com.ensayo.casapp.registro.domain.port.in;

import java.util.List;

import com.ensayo.casapp.registro.domain.models.User;

public interface IFindByFilters {
    List<User> findByFilters(String name, String lasname, String rol);
}
