package backend_principal_tienda.controller;

import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/backend-principal-tienda/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductoUpdateDto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

<<<<<<< Updated upstream
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getById(@PathVariable Integer id) {
=======
    @GetMapping("findById/{id}")
    public ResponseEntity<ProductoUpdateDto> getById(@PathVariable Integer id) {
>>>>>>> Stashed changes
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PostMapping
<<<<<<< Updated upstream
    public ResponseEntity<ProductoDto> create(@RequestBody ProductoCreateDto dto) {
=======
    public ResponseEntity<ProductoUpdateDto> create(@Valid @RequestBody ProductoCreateDto dto) {
>>>>>>> Stashed changes
        return new ResponseEntity<>(productoService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoUpdateDto> update(
            @PathVariable Integer id,
<<<<<<< Updated upstream
            @RequestBody ProductoDto dto) {
=======
            @Valid @RequestBody ProductoUpdateDto dto) {
>>>>>>> Stashed changes
        return ResponseEntity.ok(productoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
