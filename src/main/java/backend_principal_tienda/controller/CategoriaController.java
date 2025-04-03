package backend_principal_tienda.controller;

import backend_principal_tienda.dto.Update.CategoriaUpdateDto;
import backend_principal_tienda.dto.create.CategoriaCreateDto;
import backend_principal_tienda.mapper.CategoriaMapper;
import backend_principal_tienda.service.CategoriaService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoriaUpdateDto>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<CategoriaUpdateDto> getCategoriaById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaUpdateDto> createCategoria(@RequestBody CategoriaCreateDto categoriaCreateDto) {
        return new ResponseEntity<>(categoriaService.create(categoriaCreateDto), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaUpdateDto> update(
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
