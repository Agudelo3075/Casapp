package com.ensayo.casapp.registro.infraestructure.controller;

 
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        try {
            User created = registerService.createUser(userMapper.toDomain(customerDto));
            return new ResponseEntity<>(userMapper.toDto(created), HttpStatus.CREATED);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody AdminDto adminDto){
        try {
            User created = registerService.createUser(userMapper.toDomain(adminDto));
            return new ResponseEntity<>(userMapper.toDto(created), HttpStatus.CREATED);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(
            @Parameter(description = "ID del cliente a actualizar") @PathVariable Long id, 
            @Valid @RequestBody CustomerDto customerDto){
            try {
                User user = userMapper.toDomain(customerDto);
                Optional<User> updated = registerService.update(id, user);
            
                return updated.map(value -> {
                    UserDto returned = userMapper.toDto(value);
                    return new ResponseEntity<>(returned, HttpStatus.OK);
            }).orElseGet(() -> {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(
        @Parameter(description = "ID del administrador a actualizar") @PathVariable Long id,
        @Valid @RequestBody AdminDto adminDto){
            try {
                User user = userMapper.toDomain(adminDto);
                Optional<User> updated = registerService.update(id, user);

                return updated.map(value -> {
                    UserDto returned = userMapper.toDto(value);
                    return new ResponseEntity<>(returned, HttpStatus.OK);
                }).orElseGet(() -> {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
            } catch (IllegalArgumentException e){
                return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                throw e;
            }
        }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(
            @Parameter(description = "ID del usuario a buscar") @PathVariable Long id){
        try {
            Optional<User> user = registerService.getById(id);

            return user.map(value -> {
                UserDto returned = userMapper.toDto(value);
                return new ResponseEntity<>(returned, HttpStatus.OK);
            }).orElseGet(() -> {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<?> findByFilters(
        @Parameter(description = "Nombre del usuario") @RequestParam(required = false) String name,
        @Parameter(description = "Apellido del usuario") @RequestParam(required = false) String lastName,
        @Parameter(description = "Rol del usuario") @RequestParam(required = false) String rol){
            List<User> users = registerService.findByFilters(lastName, name, rol);

            if (!users.isEmpty()) {
                List<UserDto> userDtos = users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

                return new ResponseEntity<>(userDtos, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> deleteUSer(
    @Parameter(description = "id del usuario") @PathVariable Long id){
        Optional<User> user = registerService.delete(id);
        
        try {
            return user.map(value -> {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }).orElseGet(() -> {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch (Exception e) {
            throw e;
        }

    }
}
