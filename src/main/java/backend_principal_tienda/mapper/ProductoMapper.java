package backend_principal_tienda.mapper;
import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoUpdateDto toDto(Producto producto) {
        if (producto == null) return null;

<<<<<<< Updated upstream
        return ProductoDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoriaId(producto.getCategoria().getId())
                .categoriaNombre(producto.getCategoria().getNombre())
=======
        return ProductoUpdateDto.builder()
                .nameProduct(producto.getProductName())
                .codigoProducto(producto.getCodProduct())
                .descriptionProduct(producto.getDescriptionName())
                .priceProduct(producto.getPriceProduct())
                .stockProduct(producto.getStockProduct())
                .idCategory(producto.getCategoryProduct() != null ?
                        producto.getCategoryProduct().getIdCategory() : null)
                .categoryProduct(producto.getCategoryProduct())
>>>>>>> Stashed changes
                .build();
    }

    public Producto toEntity(ProductoCreateDto dto) {
        return Producto.builder()
                .productName(dto.getProductName())
                .codProduct(dto.getCodigoProducto())
                .descriptionName(dto.getDescriptionName())
                .priceProduct(dto.getPriceProduct())
                .stockProduct(dto.getStockProduct())
                .build();
    }

    public void updateFromDto(ProductoUpdateDto dto, Producto entity) {
        if (dto == null || entity == null) return;

<<<<<<< Updated upstream
        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        if (dto.getPrecio() != null) entity.setPrecio(dto.getPrecio());
        if (dto.getStock() != null) entity.setStock(dto.getStock());
=======
        if (dto.getNameProduct() != null) entity.setProductName(dto.getNameProduct());
        if (dto.getDescriptionProduct() != null) entity.setDescriptionName(dto.getDescriptionProduct());
        if (dto.getPriceProduct() != null) entity.setPriceProduct(dto.getPriceProduct());
        if (dto.getStockProduct() != null) entity.setStockProduct(dto.getStockProduct());
>>>>>>> Stashed changes
    }
}