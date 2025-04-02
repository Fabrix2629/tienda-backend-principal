package backend_principal_tienda.dto.Update;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateDto {
    private Integer id;
    private String codigo;
    private String nombre;
    private String usuario;
    private String clave;
}
