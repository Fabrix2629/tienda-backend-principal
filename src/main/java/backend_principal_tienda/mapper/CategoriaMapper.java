package backend_principal_tienda.mapper;

import backend_principal_tienda.dto.Update.CategoriaDto;
import backend_principal_tienda.dto.create.CategoriaCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.exceptions.InvalidTypeException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategoriaMapper {

    public CategoriaDto toDto(Categoria categoria) {
        if (categoria == null) return null;

        return CategoriaDto.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    public Categoria toEntity(CategoriaCreateDto dto) {
        if (dto == null) return null;

        return Categoria.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}