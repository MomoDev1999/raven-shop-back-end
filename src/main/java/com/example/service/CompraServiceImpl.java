package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Compra;
import com.example.repository.CompraRepository;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    @Override
    public Optional<Compra> findById(long id) {
        return compraRepository.findById(id);
    }

    @Override
    public List<Compra> createCompras(List<Compra> compras) {
        return compraRepository.saveAll(compras);
    }

    @Override
    public Compra updateCompra(Long id, Compra compra) {
        if (compraRepository.existsById(id)) {
            compra.setId(id);
            return compraRepository.save(compra);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        compraRepository.deleteById(id);
    }

    @Override
    public List<Compra> findByPersonaId(long idPersona) {
        return compraRepository.findByPersonaId(idPersona);
    }

}
