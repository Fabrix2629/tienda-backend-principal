package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.Update.UsuarioUpdateDto;
import backend_principal_tienda.dto.create.UsuarioCreateDto;
import backend_principal_tienda.entity.Producto;
import backend_principal_tienda.entity.Usuario;
import backend_principal_tienda.exceptions.ConflictException;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.UsuarioMapper;
import backend_principal_tienda.repository.UsuarioRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private  final UsuarioRepository usuarioRepository;
    private  final UsuarioMapper usuarioMapper;

    private String generarCodigoUsuario() {
        Optional<Integer> maxNumber = usuarioRepository.findMaxUserCodeNumber();
        int nextNumber = maxNumber.orElse(0) + 1;
        return String.format("USR-%03d", nextNumber);
    }
    public List<UsuarioUpdateDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioUpdateDto findById(Integer id) {
        return usuarioMapper.toDto(usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")));
    }

    public UsuarioUpdateDto create(UsuarioCreateDto dto) {
        if (usuarioRepository.existsByUsername(dto.getUsuario())) {
            throw new ConflictException("El nombre de usuario '" + dto.getUsuario() + "' ya está en uso");
        }
        Usuario usuario = usuarioMapper.toEntity(dto);
        if (dto.getClave() == null || dto.getClave().isEmpty()) {
            throw new ConflictException("Debe ingresar una clave");
        }
        String nuevoCodigo = generarCodigoUsuario();
        usuario.setCodUser(nuevoCodigo);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public UsuarioUpdateDto update(Integer id, UsuarioUpdateDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (dto.getUsuario() != null &&
                !dto.getUsuario().equals(usuario.getName()) &&
                usuarioRepository.existsByUsername(dto.getUsuario())) {
            throw new ConflictException("Nombre de usuario en uso");
        }
        usuario.setName(dto.getNombre());
        usuario.setUsername(dto.getUsuario());

        if (dto.getClave() != null && !dto.getClave().isEmpty()) {
            usuario.setPassword(dto.getClave());
        }else{
            throw new ConflictException("Debe ingresar una clave");
        }

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public void delete(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
