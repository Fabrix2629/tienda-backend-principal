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
<<<<<<< Updated upstream
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Integer categoriaId;
=======
    private String productName;
    private String codigoProducto;
    private String descriptionName;
    private Double priceProduct;
    private Integer stockProduct;
    private Integer idCategory;

>>>>>>> Stashed changes
}
