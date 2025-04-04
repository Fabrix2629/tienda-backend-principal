package backend_principal_tienda.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategory;

    @Column(name = "codigo",length = 50, unique = true, nullable = false)
    private String codCategory;

    @Column(name = "nombre_categoria", length = 100)
    private String nameCategory;

    @Column(name = "descripcion")
    private String descriptionCategory;

    @PrePersist
    private void generarCodigo() {
        if (codCategory == null || codCategory.isEmpty()) {
            this.codCategory = "CAT-" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();
        }
    }
}
