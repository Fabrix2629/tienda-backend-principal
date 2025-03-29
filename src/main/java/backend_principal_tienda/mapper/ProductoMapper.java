package backend_principal_tienda.mapper;
import backend_principal_tienda.dto.Update.CategoriaDto;
import backend_principal_tienda.dto.Update.ProductoDto;
import backend_principal_tienda.dto.Update.UsuarioDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.entity.Producto;
import backend_principal_tienda.entity.Usuario;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductoMapper {

    public ProductoDto toDto(Producto producto) {
        if (producto == null) return null;

        return ProductoDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoriaId(producto.getCategoria().getId())
                .categoriaNombre(producto.getCategoria().getNombre())
                .build();
    }

    public Producto toEntity(ProductoCreateDto dto) {
        if (dto == null) return null;

        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }

    public void updateFromDto(ProductoDto dto, Producto entity) {
        if (dto == null || entity == null) return;

        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        if (dto.getPrecio() != null) entity.setPrecio(dto.getPrecio());
        if (dto.getStock() != null) entity.setStock(dto.getStock());
    }
}
