package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Compra;

public interface CompraService {

    List<Compra> findAll();

    Optional<Compra> findById(long id);

    List<Compra> createCompras(List<Compra> compras);

    Compra updateCompra(Long id, Compra compra);

    void deleteById(long id);

    List<Compra> findByPersonaId(long idPersona);
}
