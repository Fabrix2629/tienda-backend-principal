package backend_principal_tienda.controller;

import backend_principal_tienda.dto.Update.UsuarioUpdateDto;
import backend_principal_tienda.dto.create.UsuarioCreateDto;
import backend_principal_tienda.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/backend-principal-tienda/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/findAll")
    public ResponseEntity<List<UsuarioUpdateDto>> findAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<UsuarioUpdateDto> getUsuarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioUpdateDto> createUsuario(
            @RequestBody UsuarioCreateDto usuarioCreateDto) {
        return new ResponseEntity<>(usuarioService.create(usuarioCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioUpdateDto> update(
            @PathVariable Integer id,
            @RequestBody UsuarioUpdateDto dto) {
        return ResponseEntity.ok(usuarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
