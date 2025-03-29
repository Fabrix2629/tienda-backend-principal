package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.UsuarioDto;
import backend_principal_tienda.dto.create.UsuarioCreateDto;
import backend_principal_tienda.entity.Usuario;
import backend_principal_tienda.exceptions.ConflictException;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.ProductoMapper;
import backend_principal_tienda.mapper.UsuarioMapper;
import backend_principal_tienda.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional(readOnly = true)
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioDto findById(Integer id) {
        return usuarioMapper.toDto(usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")));
    }

    @Transactional
    public UsuarioDto create(UsuarioCreateDto dto) {
        if (usuarioRepository.existsByUsuario(dto.getUsuario())) {
            throw new ConflictException("El nombre de usuario '" + dto.getUsuario() + "' ya está en uso");
        }
        Usuario usuario = usuarioMapper.toEntity(dto);
        if (dto.getClave() == null || dto.getClave().isEmpty()) {
            throw new ConflictException("Debe ingresar una clave");
        }
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioDto update(Integer id, UsuarioDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (dto.getUsuario() != null &&
                !dto.getUsuario().equals(usuario.getUsuario()) &&
                usuarioRepository.existsByUsuario(dto.getUsuario())) {
            throw new ConflictException("Nombre de usuario en uso");
        }
        usuario.setNombre(dto.getNombre());
        usuario.setUsuario(dto.getUsuario());

        if (dto.getClave() != null && !dto.getClave().isEmpty()) {
            usuario.setClave(dto.getClave());
        }else{
            throw new ConflictException("Debe ingresar una clave");
        }

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Transactional
    public void delete(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
