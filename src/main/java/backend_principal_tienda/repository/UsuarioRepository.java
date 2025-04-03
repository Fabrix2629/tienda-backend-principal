package backend_principal_tienda.repository;

import backend_principal_tienda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByUsername(String username);
    @Query("SELECT MAX(CAST(SUBSTRING(u.codUser, 5) AS int)) FROM Usuario u WHERE u.codUser LIKE 'USR-%'")
    Optional<Integer> findMaxUserCodeNumber();

}