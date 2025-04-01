package backend_principal_tienda.controller;

import backend_principal_tienda.dto.Update.CategoriaDto;
import backend_principal_tienda.dto.create.CategoriaCreateDto;
import backend_principal_tienda.mapper.CategoriaMapper;
import backend_principal_tienda.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/backend-principal-tienda/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoriaDto>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody CategoriaCreateDto categoriaCreateDto) {
        return new ResponseEntity<>(categoriaService.create(categoriaCreateDto), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(categoriaService.update(id, updates));
    }

    @DeleteMapping("/{id}/with-products")
    public ResponseEntity<Void> deleteWithProducts(@PathVariable Integer id) {
        categoriaService.deleteWithProducts(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deteleCategoria(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
