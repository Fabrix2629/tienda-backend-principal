package backend_principal_tienda.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreateDto {

    private String productName;
    private String codigoProducto;
    private String descriptionName;
    private Double priceProduct;
    private Integer stockProduct;
    private Integer idCategory;
}
