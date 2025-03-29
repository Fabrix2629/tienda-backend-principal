package backend_principal_tienda.dto.Update;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private Integer id;
    private String nombre;
    private String usuario;
    private String clave;
}
