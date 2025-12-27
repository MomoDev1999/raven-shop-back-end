package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Producto;

public interface ProductoService {

    List<Producto> findAll();

    Optional<Producto> findById(long id);

    Producto createProducto(Producto producto);

    Producto updateProducto(Long id, Producto producto);

    void deleteById(long id);

    List<Producto> searchByKeyword(String keyword);
}
