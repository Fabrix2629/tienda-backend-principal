package backend_principal_tienda.repository;

import backend_principal_tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
<<<<<<< Updated upstream
    boolean existsByCategoriaId(Integer categoriaId);
=======
    @Query("SELECT MAX(CAST(SUBSTRING(p.codProduct, 6) AS int)) FROM Producto p WHERE p.codProduct LIKE 'PROD-%'")
    Optional<Integer> findMaxProductCodeNumber();
>>>>>>> Stashed changes
}
