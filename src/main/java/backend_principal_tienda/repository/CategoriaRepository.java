package backend_principal_tienda.repository;

import backend_principal_tienda.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Modifying
    @Query("DELETE FROM Producto p WHERE p.categoria.id = :categoriaId")
    void deleteProductosByCategoriaId(@Param("categoriaId") Integer categoriaId);
}
