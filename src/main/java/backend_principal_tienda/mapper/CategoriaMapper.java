package backend_principal_tienda.mapper;

import backend_principal_tienda.dto.Update.CategoriaUpdateDto;
import backend_principal_tienda.dto.create.CategoriaCreateDto;
import backend_principal_tienda.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaUpdateDto toDto(Categoria categoria) {
        if (categoria == null) return null;

        return CategoriaUpdateDto.builder()
                .nameCategory(categoria.getNameCategory())
                .codigoCategory(categoria.getCodCategory())
                .descriptionCategory(categoria.getDescriptionCategory())
                .build();
    }

    public Categoria toEntity(CategoriaCreateDto dto) {
        if (dto == null) return null;

        return Categoria.builder()
                .nameCategory(dto.getNombre())
                .codCategory(dto.getCodigoCategory())
                .descriptionCategory(dto.getDescripcion())
                .build();
    }
}