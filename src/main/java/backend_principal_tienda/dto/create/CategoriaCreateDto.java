package backend_principal_tienda.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaCreateDto {
    private String nombre;
    private String descripcion;
}
