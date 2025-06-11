package com.ensayo.casapp.registro.infraestructure.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class UserDto {

    protected Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50,message = "El nombre debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo contener letras, numeros y espacios")
    protected String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
    protected String lastName;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    protected String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=\\S+$).{8,}$",
        message = "La contraseña debe contener al menos un número, una letra minúscula, una mayúscula y un carácter especial"
    )
    protected String password;

    @Pattern(
        regexp = "^\\+?[0-9]{10,15}$",
        message = "El número de teléfono debe tener entre 10 y 15 dígitos y puede comenzar con +"
    )
    protected String phone;


}
