package backend_principal_tienda.dto.Update;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Integer categoriaId;
    private String categoriaNombre;
}