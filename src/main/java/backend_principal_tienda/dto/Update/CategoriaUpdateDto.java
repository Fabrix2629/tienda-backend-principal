package backend_principal_tienda.dto.Update;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaUpdateDto {
    private String nameCategory;
    private String codigoCategory;
    private String descriptionCategory;
}
