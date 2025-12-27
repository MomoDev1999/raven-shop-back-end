package com.example.service;

import com.example.model.Persona;
import com.example.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Page<Persona> findAll(Pageable pageable) {
        return personaRepository.findAll(pageable);
    }

    @Override
    public Optional<Persona> findById(long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Persona createPersona(Persona persona) {
        // Guardar contraseña en texto plano (solo para propósitos educativos)
        return personaRepository.save(persona);
    }

    @Override
    public Persona updatePersona(Long id, Persona persona) {
        if (personaRepository.existsById(id)) {
            persona.setId(id);
            return personaRepository.save(persona);
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Optional<Persona> authenticate(String emailOrUser, String password) {
        return personaRepository.findByEmailOrUser(emailOrUser, emailOrUser)
                .filter(persona -> persona.getPassword().equals(password));
    }

    @Override
    public boolean emailExists(String email) {
        return personaRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean userExists(String user) {
        return personaRepository.findAll().stream().anyMatch(p -> p.getUser().equals(user));
    }
}
