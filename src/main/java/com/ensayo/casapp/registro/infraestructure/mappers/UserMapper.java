package com.ensayo.casapp.registro.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.ensayo.casapp.registro.domain.models.Admin;
import com.ensayo.casapp.registro.domain.models.Customer;
import com.ensayo.casapp.registro.domain.models.User;
import com.ensayo.casapp.registro.infraestructure.dtos.AdminDto;
import com.ensayo.casapp.registro.infraestructure.dtos.CustomerDto;
import com.ensayo.casapp.registro.infraestructure.dtos.UserDto;
import com.ensayo.casapp.registro.infraestructure.entities.AdminEntity;
import com.ensayo.casapp.registro.infraestructure.entities.CustomerEntity;
import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;
import com.ensayo.casapp.registro.infraestructure.exceptions.InfraestructureException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final AdminMapper adminMapper;
    private final CustomerMapper customerMapper;

    public User toDomain(UserDto userDto){
        if (userDto instanceof CustomerDto) {
            CustomerDto customerDto = (CustomerDto) userDto;
            customerDto.setRol("CLIENTE");
            return customerMapper.toDomain(customerDto);
        }else if(userDto instanceof AdminDto){
            AdminDto AdminDto = (AdminDto) userDto;
            AdminDto.setRol("ADMIN");
            return adminMapper.toDomain(AdminDto);
        }
        throw new InfraestructureException("No se pudo determinar el tipo de usuario. Debe incluir birthDate para cliente o area para el administrador");
    }

    public UserEntity toEntity(User user){
        if(user instanceof Customer){
            return customerMapper.toEntity((Customer) user);
        }else if(user instanceof Admin){
            return adminMapper.toEntity((Admin) user);
        } 
        throw new InfraestructureException("Tipo de usuario no encontrado" + user.getClass().getSimpleName());
    }

    public User toDomain(UserEntity userEntity){
        if (userEntity instanceof CustomerEntity) {
            return customerMapper.toDomain((CustomerEntity) userEntity);
        }else if (userEntity instanceof AdminEntity) {
            return adminMapper.toDomain((AdminEntity) userEntity);
        }
        throw new InfraestructureException("Tipo de usuario no soportado" + userEntity.getClass().getSimpleName());
    }

    public UserDto toDto(User user){
        if (user instanceof Customer) {
            return customerMapper.toDto((Customer) user);
        } else if(user instanceof Admin){
            return adminMapper.toDto((Admin) user);
        }
        throw new InfraestructureException("Tipo de usuario no encontrado" + user.getClass().getSimpleName());
    }
}
