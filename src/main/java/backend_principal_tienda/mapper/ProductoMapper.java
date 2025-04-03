package backend_principal_tienda.mapper;
import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoUpdateDto toDto(Producto producto) {
        if (producto == null) return null;
        return ProductoUpdateDto.builder()
                .nameProduct(producto.getProductName())
                .codigoProducto(producto.getCodProduct())
                .descriptionProduct(producto.getDescriptionName())
                .priceProduct(producto.getPriceProduct())
                .stockProduct(producto.getStockProduct())
                .idCategory(producto.getCategoryProduct() != null ?
                        producto.getCategoryProduct().getIdCategory() : null)
                .categoryProduct(producto.getCategoryProduct())
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
        if (dto.getNameProduct() != null) entity.setProductName(dto.getNameProduct());
        if (dto.getDescriptionProduct() != null) entity.setDescriptionName(dto.getDescriptionProduct());
        if (dto.getPriceProduct() != null) entity.setPriceProduct(dto.getPriceProduct());
        if (dto.getStockProduct() != null) entity.setStockProduct(dto.getStockProduct());
    }
}