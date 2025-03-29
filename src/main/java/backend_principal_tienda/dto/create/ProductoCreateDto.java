package backend_principal_tienda.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreateDto {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Integer categoriaId;
}
