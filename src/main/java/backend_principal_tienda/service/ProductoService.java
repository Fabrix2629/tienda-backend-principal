package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.entity.Producto;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.ProductoMapper;
import backend_principal_tienda.repository.CategoriaRepository;
import backend_principal_tienda.repository.ProductoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final  ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;


    private String generarCodigoProducto() {
        Optional<Integer> maxNumber = productoRepository.findMaxProductCodeNumber();
        int nextNumber = maxNumber.orElse(0) + 1;
        return String.format("PROD-%03d", nextNumber);
    }
    public List<ProductoUpdateDto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }
    public ProductoUpdateDto findById(Integer id) {
        return productoMapper.toDto(productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado")));
    }

    public ProductoUpdateDto create(ProductoCreateDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del producto son requeridos");
        }
        Categoria categoria = categoriaRepository.findById(dto.getIdCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getIdCategory()));

        Producto producto = productoMapper.toEntity(dto);
        producto.setCategoryProduct(categoria);
        String nuevoCodigo = generarCodigoProducto();
        producto.setCodProduct(nuevoCodigo);
        Producto producSave = productoRepository.save(producto);

        return productoMapper.toDto(productoRepository.save(producto));
    }

    public ProductoUpdateDto update(Integer id, ProductoUpdateDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        productoMapper.updateFromDto(dto, producto);

        if (dto.getCategoryProduct() != null && dto.getCategoryProduct().getIdCategory() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoryProduct().getIdCategory())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
            producto.setCategoryProduct(categoria);
        }

        return productoMapper.toDto(productoRepository.save(producto));
    }

    public void delete(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
