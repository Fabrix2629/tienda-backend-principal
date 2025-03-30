package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.CategoriaDto;
import backend_principal_tienda.dto.create.CategoriaCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.exceptions.InvalidTypeException;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.CategoriaMapper;
import backend_principal_tienda.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;
    private CategoriaCreateDto createDto;
    private CategoriaDto dto;

    @BeforeEach
    void setUp() {
        // Datos de prueba comunes
        categoria = Categoria.builder()
                .id(1)
                .nombre("Electrónicos")
                .descripcion("Productos electrónicos")
                .build();

        createDto = new CategoriaCreateDto("Electrónicos", "Productos electrónicos");
        dto = CategoriaDto.builder()
                .id(1)
                .nombre("Electrónicos")
                .descripcion("Productos electrónicos")
                .build();
    }

    @Test
    @DisplayName("Debería retornar todas las categorías")
    void findAll_ShouldReturnAllCategories() {
        // Arrange
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        when(categoriaMapper.toDto(categoria)).thenReturn(dto);

        // Act
        List<CategoriaDto> result = categoriaService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));

        verify(categoriaRepository, times(1)).findAll();
        verify(categoriaMapper, times(1)).toDto(categoria);
    }

    @Test
    @DisplayName("Debería retornar categoría cuando el ID existe")
    void findById_WhenIdExists_ShouldReturnCategory() {
        // Arrange
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toDto(categoria)).thenReturn(dto);

        // Act
        CategoriaDto result = categoriaService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);

        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaMapper, times(1)).toDto(categoria);
    }
    @Test
    @DisplayName("Debería lanzar excepción cuando el ID no existe")
    void findById_WhenIdNotExists_ShouldThrowException() {
        // Arrange
        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            categoriaService.findById(99);
        });

        verify(categoriaRepository, times(1)).findById(99);
        verify(categoriaMapper, never()).toDto(any());
    }
    @Test
    @DisplayName("Debería crear una nueva categoría")
    void create_ShouldSaveNewCategory() {
        // Arrange
        when(categoriaMapper.toEntity(createDto)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toDto(categoria)).thenReturn(dto);

        // Act
        CategoriaDto result = categoriaService.create(createDto);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);

        verify(categoriaMapper, times(1)).toEntity(createDto);
        verify(categoriaRepository, times(1)).save(categoria);
        verify(categoriaMapper, times(1)).toDto(categoria);
    }

    @Test
    @DisplayName("Debería actualizar una categoría existente")
    void update_ShouldUpdateExistingCategory() {
        // Arrange
        Map<String, Object> updates = Map.of(
                "nombre", "Electrodomésticos",
                "descripcion", "Nueva descripción"
        );

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toDto(categoria)).thenReturn(dto);

        // Act
        CategoriaDto result = categoriaService.update(1, updates);

        // Assert
        assertNotNull(result);
        assertEquals("Electrodomésticos", categoria.getNombre());
        assertEquals("Nueva descripción", categoria.getDescripcion());

        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaRepository, times(1)).save(categoria);
        verify(categoriaMapper, times(1)).toDto(categoria);
    }

    @Test
    @DisplayName("Debería eliminar categoría y sus productos")
    void deleteWithProducts_ShouldDeleteCategoryAndProducts() {
        // Arrange
        when(categoriaRepository.existsById(1)).thenReturn(true);

        // Act
        categoriaService.deleteWithProducts(1);

        // Assert
        verify(categoriaRepository, times(1)).existsById(1);
        verify(categoriaRepository, times(1)).deleteProductosByCategoriaId(1);
        verify(categoriaRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Debería lanzar excepción al eliminar categoría inexistente")
    void deleteWithProducts_WhenCategoryNotExists_ShouldThrowException() {
        // Arrange
        when(categoriaRepository.existsById(99)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            categoriaService.deleteWithProducts(99);
        });

        verify(categoriaRepository, times(1)).existsById(99);
        verify(categoriaRepository, never()).deleteProductosByCategoriaId(any());
        verify(categoriaRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Debería lanzar excepción con tipo de campo inválido")
    void update_WithInvalidFieldType_ShouldThrowException() {
        // Arrange
        Map<String, Object> updates = Map.of("nombre", 123); // Int en lugar de String

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        // Act & Assert
        assertThrows(InvalidTypeException.class, () -> {
            categoriaService.update(1, updates);
        });

        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaRepository, never()).save(any());
    }
}
