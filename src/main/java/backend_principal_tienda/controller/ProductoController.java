package backend_principal_tienda.controller;

import backend_principal_tienda.dto.Update.ProductoDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend-principal-tienda/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductoDto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDto> create(@RequestBody ProductoCreateDto dto) {
        return new ResponseEntity<>(productoService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update(
            @PathVariable Integer id,
            @RequestBody ProductoDto dto) {
        return ResponseEntity.ok(productoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
