package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.CategoriaUpdateDto;
import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.create.CategoriaCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.entity.Producto;
import backend_principal_tienda.exceptions.InvalidTypeException;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.CategoriaMapper;
import backend_principal_tienda.repository.CategoriaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final  CategoriaRepository categoriaRepository;
    private final  CategoriaMapper categoriaMapper;

    private String generarCodigoCategoria() {
        Optional<Integer> maxNumber = categoriaRepository.findMaxCategoryCodeNumber();
        int nextNumber = maxNumber.orElse(0) + 1;
        return String.format("CAT-%03d", nextNumber);
    }
    public List<CategoriaUpdateDto> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }
    public CategoriaUpdateDto findById(Integer id) {
        return categoriaMapper.toDto(categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada")));
    }

    public CategoriaUpdateDto create(CategoriaCreateDto dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        String nuevoCodigo = generarCodigoCategoria();
        categoria.setCodCategory(nuevoCodigo);
        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    public CategoriaUpdateDto update(Integer id, Map<String, Object> updates) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        if (updates.containsKey("nombre")) {
            if (!(updates.get("nombre") instanceof String)) {
                throw new InvalidTypeException("nombre", "String");
            }
            categoria.setNameCategory((String) updates.get("nombre"));
        }

        if (updates.containsKey("descripcion")) {
            if (updates.get("descripcion") != null && !(updates.get("descripcion") instanceof String)) {
                throw new InvalidTypeException("descripcion", "String");
            }
            categoria.setDescriptionCategory((String) updates.get("descripcion"));
        }

        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    public void deleteById(Integer id){
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }
    public void deleteWithProducts(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada");
        }
        categoriaRepository.deleteProductosByCategoriaId(id);
        categoriaRepository.deleteById(id);
    }
}
