package com.ensayo.casapp.registro.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncripterPasswordUseCase {
    private PasswordEncoder passwordEncoder;

    public String encripter(String password, String email){
        String passwordEncripted = passwordEncoder.encode(password);
        return passwordEncripted; 
    }
}
