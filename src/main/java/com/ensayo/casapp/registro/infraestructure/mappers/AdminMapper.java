package com.ensayo.casapp.registro.infraestructure.mappers;

import com.ensayo.casapp.registro.domain.models.Admin;
import com.ensayo.casapp.registro.infraestructure.dtos.AdminDto;
import com.ensayo.casapp.registro.infraestructure.entities.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "id", ignore = true)
    AdminEntity toEntity(Admin admin);
    
    Admin toDomain(AdminEntity entity);
    
    AdminDto toDto(Admin admin);
    
    Admin toDomain(AdminDto dto);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDomain(Admin admin, @MappingTarget AdminEntity entity);
    
    void updateDomainFromDto(AdminDto dto, @MappingTarget Admin admin);

}