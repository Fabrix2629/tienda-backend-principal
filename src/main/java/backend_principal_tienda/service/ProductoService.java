package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.ProductoDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.entity.Producto;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.ProductoMapper;
import backend_principal_tienda.repository.CategoriaRepository;
import backend_principal_tienda.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    @Transactional(readOnly = true)
    public List<ProductoDto> findAll() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductoDto findById(Integer id) {
        return productoMapper.toDto(productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado")));
    }

    @Transactional
    public ProductoDto create(ProductoCreateDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del producto son requeridos");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoria()));

        Producto producto = productoMapper.toEntity(dto);
        producto.setCategoria(categoria);

        Producto productoGuardado = productoRepository.save(producto);

        return productoMapper.toDto(productoGuardado);
    }

    @Transactional
    public ProductoDto update(Integer id, ProductoDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        // Validar y actualizar categoría si viene en el DTO
        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }

        productoMapper.updateFromDto(dto, producto);
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Transactional
    public void delete(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
