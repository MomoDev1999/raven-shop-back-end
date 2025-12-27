package com.example.controller;

import com.example.dto.ProductoDTO;
import com.example.dto.RatingDTO;
import com.example.model.Producto;
import com.example.model.Rating;
import com.example.service.ProductoService;
import com.example.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<ProductoDTO> findAll() {
        // Convertimos la lista de Productos a ProductoDTO para incluir el rating
        return productoService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductoDTO findById(@PathVariable long id) {
        // Buscamos el producto por ID y convertimos a DTO
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertToDto(producto);
    }

    @PostMapping
    public List<Producto> create(@RequestBody List<Producto> productos) {
        // Crear múltiples productos
        return productos.stream()
                .map(productoService::createProducto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable long id, @RequestBody Producto producto) {
        // Actualizar un producto existente
        Producto updatedProducto = productoService.updateProducto(id, producto);
        if (updatedProducto == null) {
            throw new RuntimeException("Producto no encontrado para actualizar");
        }
        return updatedProducto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        // Eliminar un producto por ID
        productoService.deleteById(id);
    }

    // Nuevo endpoint de búsqueda
    @GetMapping("/search")
    public List<ProductoDTO> searchByKeyword(@RequestParam String keyword) {
        return productoService.searchByKeyword(keyword).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un Producto en un ProductoDTO e incluye la información de Rating
     */
    private ProductoDTO convertToDto(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setTitle(producto.getTitle());
        productoDTO.setPrice(producto.getPrice());
        productoDTO.setDescription(producto.getDescription());
        productoDTO.setCategory(producto.getCategory());
        productoDTO.setImage(producto.getImage());

        // Buscar ratings relacionados con este producto
        List<Rating> ratings = ratingService.findAll().stream()
                .filter(rating -> rating.getProducto().getId() == producto.getId())
                .collect(Collectors.toList());

        if (!ratings.isEmpty()) {
            Rating rating = ratings.get(0); // Asumimos un solo rating asociado
            RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.setRate(rating.getRate());
            ratingDTO.setCount(ratings.size());
            productoDTO.setRating(ratingDTO);
        }

        return productoDTO;
    }
}
