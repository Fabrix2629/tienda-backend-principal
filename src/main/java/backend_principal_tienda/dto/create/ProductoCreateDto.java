package backend_principal_tienda.dto.create;

import backend_principal_tienda.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreateDto {
    private String nameProduct;
    private String codigoProducto;
    private String descriptionProduct;
    private Double priceProduct;
    private Integer stockProduct;
    private Integer idCategory;
    private Categoria categoryProduct;
}
