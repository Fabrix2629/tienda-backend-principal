package backend_principal_tienda.dto.Update;

import backend_principal_tienda.entity.Categoria;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoUpdateDto {
    private String nameProduct;
    private String descriptionProduct;
    private String codigoProducto;
    private Double priceProduct;
    private Integer stockProduct;
    private Integer idCategory;
    private Categoria categoryProduct;

}