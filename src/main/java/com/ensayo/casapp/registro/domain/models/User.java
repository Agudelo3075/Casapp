package com.ensayo.casapp.registro.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class User {
    protected Long id;
    protected String name;
    protected String lastName;
    protected String email;
    protected String password;
    protected String phone;
    protected String rol;
}
