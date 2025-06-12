package com.ensayo.casapp.registro.domain.models;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Admin extends User{
    private String area;
}
