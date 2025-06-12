package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import com.ensayo.casapp.registro.domain.models.Admin;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateAdminUseCase {
    private final EncripterPasswordUseCase encripterPasswordUseCase;

    public Admin updateAdmin(Admin adminDb, Admin adminEntrante){
        adminDb.setId(adminDb.getId());
        adminDb.setName(adminEntrante.getName());
        adminDb.setLastName(adminEntrante.getLastName());
        adminDb.setEmail(adminEntrante.getEmail());
        adminDb.setPassword(encripterPasswordUseCase.encripter(adminEntrante.getPassword(), adminEntrante.getEmail()));
        adminDb.setPhone(adminEntrante.getPhone());
        adminDb.setRol(adminEntrante.getRol());
        adminDb.setArea(adminEntrante.getArea());
        return adminDb;
    }
}
