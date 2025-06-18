package com.ensayo.casapp.registro.infraestructure.controller;

 
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensayo.casapp.registro.application.service.RegisterService;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.infraestructure.dtos.AdminDto;
import com.ensayo.casapp.registro.infraestructure.dtos.CustomerDto;
import com.ensayo.casapp.registro.infraestructure.dtos.UserDto;
import com.ensayo.casapp.registro.infraestructure.logging.LoggingService;
import com.ensayo.casapp.registro.infraestructure.mappers.UserMapper;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@Tag(name = "Registro usuarios", description = "API para gestionar el registro de productos (clientes y admistradores)")

public class RegisterController {
    private final RegisterService registerService;
    private final UserMapper userMapper;
    private final LoggingService loggingService;

    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        loggingService.logInfo("Inicializando registro de cliente con -Email: " + customerDto.getEmail());
        try {
            User created = registerService.createUser(userMapper.toDomain(customerDto));
            loggingService.logDebug("Cliente registrado exitosamente con -ID: " + created.getId());
            return new ResponseEntity<>(userMapper.toDto(created), HttpStatus.CREATED);
        } catch (Exception e) {
            loggingService.logError("Error al registrar cliente", e.getMessage(),e);
            throw e;
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody AdminDto adminDto){
        loggingService.logInfo("Inicializando registro de admin con email -Email: " + adminDto.getEmail());
        try {
            User created = registerService.createUser(userMapper.toDomain(adminDto));
            loggingService.logDebug("Administrador registrado exitosamente con -ID: " + created.getId());
            return new ResponseEntity<>(userMapper.toDto(created), HttpStatus.CREATED);
        } catch (Exception e) {
            loggingService.logError("Error al registrar admin" +  e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(
            @Parameter(description = "ID del cliente a actualizar") @PathVariable Long id, 
            @Valid @RequestBody CustomerDto customerDto){
            loggingService.logInfo("Inicializando proceso de actualización del cliente con -Email: " + customerDto.getEmail());
            try {
                User user = userMapper.toDomain(customerDto);
                loggingService.logDebug("Cliente mapeado a dominio", user.getEmail());
                Optional<User> updated = registerService.update(id, user);
            
                return updated.map(value -> {
                    UserDto returned = userMapper.toDto(value);
                    loggingService.logDebug("Cliente actualizado exitosamente con email: " + value.getEmail());
                    return new ResponseEntity<>(returned, HttpStatus.OK);
            }).orElseGet(() -> {
                loggingService.logWarning("Intento de actualización de customer fallido ", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch (IllegalArgumentException e) {
            loggingService.logError("Error de validación al actualizar cliente: " + e.getMessage(), e);
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            loggingService.logError("Error inesperado al actualizar cliente - ID: " + id, e);
            throw e;
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(
        @Parameter(description = "ID del administrador a actualizar") @PathVariable Long id,
        @Valid @RequestBody AdminDto adminDto){
            loggingService.logInfo("Inicializando proceso de actualización del administrador con -Email: " + adminDto.getEmail());
            try {
                User user = userMapper.toDomain(adminDto);
                loggingService.logDebug("admin mapeado a dominio" + user.getEmail());
                Optional<User> updated = registerService.update(id, user);

                return updated.map(value -> {
                    UserDto returned = userMapper.toDto(value);
                    loggingService.logDebug("Admin actualizado exitosamente con -ID: ", value.getId());
                    return new ResponseEntity<>(returned, HttpStatus.OK);
                }).orElseGet(() -> {
                    loggingService.logWarning("Intento de actualizacion de administrador fallido", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
            } catch (IllegalArgumentException e){
                loggingService.logError("Error de validación al actualizar al administrador", e.getMessage(), e);
                return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                loggingService.logError("Error inesperado al actualizar administrador con -ID: " + id, e);
                throw e;
            }
        }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(
            @Parameter(description = "ID del usuario a buscar") @PathVariable Long id){
                loggingService.logInfo("Inicializando proceceso de busqueda con -ID: " + id);
        try {
            Optional<User> user = registerService.getById(id);

            return user.map(value -> {
                UserDto returned = userMapper.toDto(value);
                loggingService.logDebug("Usuario encontrado correctamente: ", value.getEmail());
                return new ResponseEntity<>(returned, HttpStatus.OK);
            }).orElseGet(() -> {
                loggingService.logWarning("Usuario no encontrado con ID:", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch (Exception e) {
            loggingService.logError("Error al buscar usuario por -ID: ", id);
            throw e;
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByFilters(
        @Parameter(description = "Nombre del usuario") @RequestParam(required = false) String name,
        @Parameter(description = "Apellido del usuario") @RequestParam(required = false) String lastName,
        @Parameter(description = "Rol del usuario") @RequestParam(required = false) String rol){
            loggingService.logInfo("Comenzando busquedad por filtros");
            List<User> users = registerService.findByFilters(lastName, name, rol);

            if (!users.isEmpty()) {
                List<UserDto> userDtos = users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

                loggingService.logDebug("Se ha encontrado exitosamnete por filtros", userDtos);
                return new ResponseEntity<>(userDtos, HttpStatus.OK);
            }
            loggingService.logInfo("No se encontraron usuarios con los filtros especificados");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUSer(
    @Parameter(description = "id del usuario") @PathVariable Long id){
        loggingService.logInfo("Iniciando eliminación de usuario con ID: " + id);
        Optional<User> user = registerService.delete(id);
        
        try {
            return user.map(value -> {
                loggingService.logDebug("Usuario eliminado exitosamente: " + value.getEmail());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }).orElseGet(() -> {
                loggingService.logWarning("Intento de eliminación de usuario inexistente con ID: " + id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch (Exception e) {
            loggingService.logError("Error al eliminar usuario - ID: " + id, e);
            throw e;
        }

    }
}
