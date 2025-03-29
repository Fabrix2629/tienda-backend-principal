package backend_principal_tienda.mapper;

import backend_principal_tienda.dto.Update.UsuarioDto;
import backend_principal_tienda.dto.create.UsuarioCreateDto;
import backend_principal_tienda.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) return null;

        return UsuarioDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .usuario(usuario.getUsuario())
                .clave(usuario.getClave())
                .build();
    }

    public Usuario toEntity(UsuarioCreateDto dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .nombre(dto.getNombre())
                .usuario(dto.getUsuario())
                .clave(dto.getClave())
                .build();
    }
    
}