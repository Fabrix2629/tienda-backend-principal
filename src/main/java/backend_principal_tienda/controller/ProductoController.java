package backend_principal_tienda.controller;

import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/backend-principal-tienda/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductoUpdateDto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ProductoUpdateDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoUpdateDto> create(@Valid  @RequestBody ProductoCreateDto productoCreateDto) {
        return new ResponseEntity<>(productoService.create(productoCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoUpdateDto> update(@PathVariable Integer id,  @Valid @RequestBody ProductoUpdateDto productoUpdateDto) {
        return ResponseEntity.ok(productoService.update(id, productoUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
