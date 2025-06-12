package com.ensayo.casapp.registro.domain.port.in;

import java.util.Optional;
import com.ensayo.casapp.registro.domain.models.User;

public interface IDelete {
    Optional<User> delete(Long id);
}
