package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.CategoriaDto;
import backend_principal_tienda.dto.create.CategoriaCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.exceptions.InvalidTypeException;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.CategoriaMapper;
import backend_principal_tienda.mapper.ProductoMapper;
import backend_principal_tienda.repository.CategoriaRepository;
import backend_principal_tienda.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Transactional(readOnly = true)
    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDto findById(Integer id) {
        return categoriaMapper.toDto(categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada")));
    }

    @Transactional
    public CategoriaDto create(CategoriaCreateDto dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaDto update(Integer id, Map<String, Object> updates) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        if (updates.containsKey("nombre")) {
            if (!(updates.get("nombre") instanceof String)) {
                throw new InvalidTypeException("nombre", "String");
            }
            categoria.setNombre((String) updates.get("nombre"));
        }

        if (updates.containsKey("descripcion")) {
            if (updates.get("descripcion") != null && !(updates.get("descripcion") instanceof String)) {
                throw new InvalidTypeException("descripcion", "String");
            }
            categoria.setDescripcion((String) updates.get("descripcion"));
        }

        return categoriaMapper.toDto(categoriaRepository.save(categoria));
    }

    @Transactional
    public void deleteWithProducts(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada");
        }
        categoriaRepository.deleteProductosByCategoriaId(id);
        categoriaRepository.deleteById(id);
    }
}
