package com.ensayo.casapp.registro.infraestructure.mappers;

import com.ensayo.casapp.registro.domain.models.Customer;
import com.ensayo.casapp.registro.infraestructure.dtos.CustomerDto;
import com.ensayo.casapp.registro.infraestructure.entities.CustomerEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    CustomerEntity toEntity(Customer customer);

    Customer toDomain(CustomerEntity customerEntity);

    CustomerDto toDto(Customer customer);

    Customer toDomain(CustomerDto customerDto);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDomain(Customer customer, @MappingTarget CustomerEntity entity);
    
    void updateDomainFromDto(CustomerDto dto, @MappingTarget Customer customer);
}
