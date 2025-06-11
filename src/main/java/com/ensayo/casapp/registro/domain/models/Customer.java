package com.ensayo.casapp.registro.domain.models;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Customer extends User{
    private LocalDate birthDate;
}
