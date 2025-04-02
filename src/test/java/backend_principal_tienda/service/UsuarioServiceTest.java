package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.UsuarioUpdateDto;
import backend_principal_tienda.dto.create.UsuarioCreateDto;
import backend_principal_tienda.entity.Usuario;
import backend_principal_tienda.exceptions.ConflictException;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.UsuarioMapper;
import backend_principal_tienda.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioUpdateDto usuarioUpdateDto;
    private UsuarioCreateDto usuarioCreateDto;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUser(1);
        usuario.setName("Juan Perez");
        usuario.setUsername("jperez");
        usuario.setPassword("password123");

        usuarioUpdateDto = new UsuarioUpdateDto();
        usuarioUpdateDto.setId(1);
        usuarioUpdateDto.setNombre("Juan Perez");
        usuarioUpdateDto.setUsuario("jperez");
        usuarioUpdateDto.setClave("password123");

        usuarioCreateDto = new UsuarioCreateDto();
        usuarioCreateDto.setNombre("Juan Perez");
        usuarioCreateDto.setUsuario("jperez");
        usuarioCreateDto.setClave("password123");
    }

    @Test
    void findAllUsuarioDto() {
        // Arrange
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioUpdateDto);

        // Act
        List<UsuarioUpdateDto> result = usuarioService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(usuarioUpdateDto, result.get(0));
        verify(usuarioRepository).findAll();
        verify(usuarioMapper).toDto(usuario);
    }

    @Test
    void findByIdWhenUsuarioExists() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioUpdateDto);

        // Act
        UsuarioUpdateDto result = usuarioService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(usuarioUpdateDto, result);
        verify(usuarioRepository).findById(1);
        verify(usuarioMapper).toDto(usuario);
    }

    @Test
    void findByIdWhenUsuarioNotExistException() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> usuarioService.findById(1));
        verify(usuarioRepository).findById(1);
    }

    @Test
    void createWithValidData() {
        // Arrange
        when(usuarioRepository.existsByUsuario("jperez")).thenReturn(false);
        when(usuarioMapper.toEntity(usuarioCreateDto)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioUpdateDto);

        // Act
        UsuarioUpdateDto result = usuarioService.create(usuarioCreateDto);

        // Assert
        assertNotNull(result);
        assertEquals(usuarioUpdateDto, result);
        verify(usuarioRepository).existsByUsuario("jperez");
        verify(usuarioMapper).toEntity(usuarioCreateDto);
        verify(usuarioRepository).save(usuario);
        verify(usuarioMapper).toDto(usuario);
    }

    @Test
    void createWithExistingUsuarioException() {
        // Arrange
        when(usuarioRepository.existsByUsuario("jperez")).thenReturn(true);

        // Act & Assert
        ConflictException exception = assertThrows(ConflictException.class,
                () -> usuarioService.create(usuarioCreateDto));

        assertEquals("El nombre de usuario 'jperez' ya está en uso", exception.getMessage());
        verify(usuarioRepository).existsByUsuario("jperez");
        verify(usuarioRepository, never()).save(any());
    }



    @Test
    void updateWhenUsuarioNotExistsFoundException() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> usuarioService.update(1, usuarioUpdateDto));
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository, never()).save(any());
    }


    @Test
    void updateWithEmptyClaveException() {
        // Arrange
        usuarioUpdateDto.setClave("");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        // Act & Assert
        ConflictException exception = assertThrows(ConflictException.class,
                () -> usuarioService.update(1, usuarioUpdateDto));

        assertEquals("Debe ingresar una clave", exception.getMessage());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deleteWhenUsuarioExists() {
        // Arrange
        when(usuarioRepository.existsById(1)).thenReturn(true);

        // Act
        usuarioService.delete(1);

        // Assert
        verify(usuarioRepository).existsById(1);
        verify(usuarioRepository).deleteById(1);
    }

    @Test
    void deleteWhenUsuarioNotExistsException() {
        // Arrange
        when(usuarioRepository.existsById(1)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> usuarioService.delete(1));
        verify(usuarioRepository).existsById(1);
        verify(usuarioRepository, never()).deleteById(any());
    }
}