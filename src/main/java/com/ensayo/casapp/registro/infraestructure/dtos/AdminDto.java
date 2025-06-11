package com.ensayo.casapp.registro.infraestructure.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminDto extends UserDto{

    @NotBlank(message = "El área no puede estar vacía")
    @Size(min = 2, max = 50, message = "El área debe tener entre 2 y 50 caracteres")
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
        message = "El área solo puede contener letras y espacios"
    )
    private String area;
}
