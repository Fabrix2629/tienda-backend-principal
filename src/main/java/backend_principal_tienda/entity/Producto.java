package backend_principal_tienda.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

import java.util.UUID;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProduct;

    @Column(name = "codigo ", length = 50, unique = true, nullable = false)
    private String codProduct;

    @Column(name = "nombre",nullable = false, length = 30)
    private String productName;

    @Column(name = "descripcion",columnDefinition = "TEXT")
    private String descriptionName;

    @Column(name = "precio",nullable = false)
    private Double priceProduct;

    @Column(name = "stock",nullable = false)
    private Integer stockProduct;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id_categoria",nullable = false)
    private Categoria categoryProduct;
    @PrePersist
    private void generarCodigo() {
        if (codProduct == null || codProduct.isEmpty()) {
            this.codProduct = "PROD-" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();
        }
    }
}
