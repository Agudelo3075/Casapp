package com.ensayo.casapp.registro.domain.port.in;

import com.ensayo.casapp.registro.domain.models.User;

public interface ISave {
    User createUser(User user);
}
