package com.ensayo.casapp.registro.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ensayo.casapp.registro.application.service.RegisterService;
import com.ensayo.casapp.registro.domain.port.in.IDelete;
import com.ensayo.casapp.registro.domain.port.in.IFindByFilters;
import com.ensayo.casapp.registro.domain.port.in.IFindById;
import com.ensayo.casapp.registro.domain.port.in.ISave;
import com.ensayo.casapp.registro.domain.port.in.IUpdate;
import com.ensayo.casapp.registro.domain.port.out.IRepositoryPort;
import com.ensayo.casapp.registro.infraestructure.repositories.AdapterRepository;

@Configuration
public class AppConfig {

    @Bean 
    @Primary
    public IRepositoryPort repositoryPort(AdapterRepository adapterRepository){
        return adapterRepository;
    }

    public RegisterService registerService(IDelete delete, IFindByFilters findByFilters, IFindById findById, ISave save, IUpdate update){
        return new RegisterService(delete, findByFilters, save, findById, update);
    }
}
