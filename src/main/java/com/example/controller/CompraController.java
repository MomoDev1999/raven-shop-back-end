package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.model.Compra;
import com.example.service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<Compra> findAll() {
        return compraService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Compra> findById(@PathVariable long id) {
        return compraService.findById(id);
    }

    @PostMapping
    public List<Compra> createCompras(@RequestBody List<Compra> compras) {
        return compraService.createCompras(compras);
    }

    @PutMapping("/{id}")
    public Compra updateCompra(@PathVariable long id, @RequestBody Compra compra) {
        return compraService.updateCompra(id, compra);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        compraService.deleteById(id);
    }

    @GetMapping("/persona/{idPersona}")
    public List<Compra> findByPersonaId(@PathVariable long idPersona) {
        return compraService.findByPersonaId(idPersona);
    }

}
