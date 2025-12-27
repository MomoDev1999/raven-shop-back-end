package com.example.controller;

import com.example.model.Persona;
import com.example.model.LoginRequest;
import com.example.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personas")

public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public Page<Persona> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return personaService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Optional<Persona> findById(@PathVariable long id) {
        return personaService.findById(id);
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }

    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable long id, @RequestBody Persona persona) {
        return personaService.updatePersona(id, persona);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        personaService.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("JSON recibido - Email: " + loginRequest.getEmail());
        System.out.println("JSON recibido - Contraseña: " + loginRequest.getPassword());

        Optional<Persona> persona = personaService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        if (persona.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true); // Agregar bandera de éxito
            Map<String, Object> user = new HashMap<>();
            user.put("id", persona.get().getId());
            user.put("email", persona.get().getEmail());
            user.put("user", persona.get().getUser());
            response.put("user", user);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false); // Indicar fallo
            response.put("message", "Credenciales incorrectas.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/usuarios/existe")
    public ResponseEntity<?> userExists(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String user = request.get("user");

        boolean emailExists = personaService.emailExists(email);
        boolean userExists = personaService.userExists(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("emailExists", emailExists);
        response.put("userExists", userExists);
        return ResponseEntity.ok(response);
    }
}
