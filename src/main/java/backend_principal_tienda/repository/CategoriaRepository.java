package backend_principal_tienda.repository;

import backend_principal_tienda.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("SELECT MAX(CAST(SUBSTRING(c.codCategory, 5) AS int)) FROM Categoria c WHERE c.codCategory LIKE 'CAT-%'")
    Optional<Integer> findMaxCategoryCodeNumber();
    @Modifying
    @Transactional
    @Query("DELETE FROM Producto p WHERE p.categoryProduct.idCategory = :categoriaId")
    void deleteProductosByCategoriaId(@Param("categoriaId") Integer categoriaId);

}
