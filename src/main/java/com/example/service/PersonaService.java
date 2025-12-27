package com.example.service;

import com.example.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PersonaService {

    Page<Persona> findAll(Pageable pageable);

    Optional<Persona> findById(long id);

    Persona createPersona(Persona persona);

    Persona updatePersona(Long id, Persona persona);

    void deleteById(long id);

    Optional<Persona> authenticate(String email, String password);

    boolean emailExists(String email);

    boolean userExists(String user);
}
