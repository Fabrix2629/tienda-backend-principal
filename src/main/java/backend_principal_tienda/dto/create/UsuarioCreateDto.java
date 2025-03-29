package backend_principal_tienda.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDto {
    private String nombre;
    private String usuario;
    private String clave;
}
