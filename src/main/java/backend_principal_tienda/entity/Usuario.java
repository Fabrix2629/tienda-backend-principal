package backend_principal_tienda.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUser;

    @Column(name = "codigo",length = 50, unique = true, nullable = false)
    private String codUser;

    @Column(name = "nombre",nullable = false, length = 100)
    private String name;

    @Column(name = "nombre_usuario",nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @PrePersist
    private void generarCodigo() {
        if (codUser == null || codUser.isEmpty()) {
            this.codUser = "USR-" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();
        }
    }
}
