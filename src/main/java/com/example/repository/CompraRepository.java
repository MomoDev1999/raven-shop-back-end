package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Compra;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByPersonaId(long idPersona);
}
