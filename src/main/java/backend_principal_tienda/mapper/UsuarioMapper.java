package backend_principal_tienda.mapper;

import backend_principal_tienda.dto.Update.UsuarioUpdateDto;
import backend_principal_tienda.dto.create.UsuarioCreateDto;
import backend_principal_tienda.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
<<<<<<< Updated upstream

    public UsuarioDto toDto(Usuario usuario) {
=======
    public UsuarioUpdateDto toDto(Usuario usuario) {
>>>>>>> Stashed changes
        if (usuario == null) return null;

        return UsuarioUpdateDto.builder()
                .id(usuario.getIdUser())
                .codigo(usuario.getCodUser())
                .nombre(usuario.getName())
                .usuario(usuario.getUsername())
                .clave(usuario.getPassword())
                .build();
    }

    public Usuario toEntity(UsuarioCreateDto dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .name(dto.getNombre())
                .codUser(dto.getCodigo())
                .username(dto.getUsuario())
                .password(dto.getClave())
                .build();
    }
    
}