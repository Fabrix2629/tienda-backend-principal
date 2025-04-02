package backend_principal_tienda.service;

import backend_principal_tienda.dto.Update.ProductoUpdateDto;
import backend_principal_tienda.dto.create.ProductoCreateDto;
import backend_principal_tienda.entity.Categoria;
import backend_principal_tienda.entity.Producto;
import backend_principal_tienda.exceptions.ResourceNotFoundException;
import backend_principal_tienda.mapper.ProductoMapper;
import backend_principal_tienda.repository.CategoriaRepository;
import backend_principal_tienda.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void findAll_ShouldReturnListOfProductoDto() {
        // Arrange
        Producto producto = new Producto();
        ProductoUpdateDto dto = new ProductoUpdateDto();
        when(productoRepository.findAll()).thenReturn(Collections.singletonList(producto));
        when(productoMapper.toDto(producto)).thenReturn(dto);

        // Act
        List<ProductoUpdateDto> result = productoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
        verify(productoRepository).findAll();
        verify(productoMapper).toDto(producto);
    }

    @Test
    void findById_WhenProductoExists_ShouldReturnProductoDto() {
        // Arrange
        Integer id = 1;
        Producto producto = new Producto();
        ProductoUpdateDto dto = new ProductoUpdateDto();
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        when(productoMapper.toDto(producto)).thenReturn(dto);

        // Act
        ProductoUpdateDto result = productoService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);
        verify(productoRepository).findById(id);
        verify(productoMapper).toDto(producto);
    }

    @Test
    void findById_WhenProductoNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        Integer id = 1;
        when(productoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productoService.findById(id));
        verify(productoRepository).findById(id);
    }

    @Test
    void create_ShouldSaveAndReturnProductoDto() {
        // Arrange
        Integer categoriaId = 1;
        ProductoCreateDto createDto = new ProductoCreateDto();
        createDto.setCategoriaId(categoriaId);

        Categoria categoria = new Categoria();
        Producto producto = new Producto();
        Producto savedProducto = new Producto();
        ProductoUpdateDto dto = new ProductoUpdateDto();

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoria));
        when(productoMapper.toEntity(createDto)).thenReturn(producto);
        when(productoRepository.save(producto)).thenReturn(savedProducto);
        when(productoMapper.toDto(savedProducto)).thenReturn(dto);

        // Act
        ProductoUpdateDto result = productoService.create(createDto);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);
        assertEquals(categoria, producto.getCategoria());
        verify(categoriaRepository).findById(categoriaId);
        verify(productoMapper).toEntity(createDto);
        verify(productoRepository).save(producto);
        verify(productoMapper).toDto(savedProducto);
    }

    @Test
    void create_WhenCategoriaNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        Integer categoriaId = 1;
        ProductoCreateDto createDto = new ProductoCreateDto();
        createDto.setCategoriaId(categoriaId);

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productoService.create(createDto));
        verify(categoriaRepository).findById(categoriaId);
        verifyNoInteractions(productoMapper, productoRepository);
    }

    @Test
    void update_WithValidDto_ShouldUpdateAndReturnProductoDto() {
        // Arrange
        Integer id = 1;
        Integer newCategoriaId = 2;
        ProductoUpdateDto dto = new ProductoUpdateDto();
        dto.setCategoriaId(newCategoriaId);

        Producto producto = new Producto();
        Categoria newCategoria = new Categoria();
        Producto updatedProducto = new Producto();
        ProductoUpdateDto resultDto = new ProductoUpdateDto();

        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        when(categoriaRepository.findById(newCategoriaId)).thenReturn(Optional.of(newCategoria));
        when(productoRepository.save(producto)).thenReturn(updatedProducto);
        when(productoMapper.toDto(updatedProducto)).thenReturn(resultDto);

        // Act
        ProductoUpdateDto result = productoService.update(id, dto);

        // Assert
        assertNotNull(result);
        assertEquals(resultDto, result);
        verify(productoRepository).findById(id);
        verify(categoriaRepository).findById(newCategoriaId);
        verify(productoMapper).updateFromDto(dto, producto);
        verify(productoRepository).save(producto);
        verify(productoMapper).toDto(updatedProducto);
        assertEquals(newCategoria, producto.getCategoria());
    }

    @Test
    void update_WithoutCategoriaChange_ShouldUpdateAndReturnProductoDto() {
        // Arrange
        Integer id = 1;
        ProductoUpdateDto dto = new ProductoUpdateDto();
        dto.setCategoriaId(null); // No cambia la categoría

        Producto producto = new Producto();
        Producto updatedProducto = new Producto();
        ProductoUpdateDto resultDto = new ProductoUpdateDto();

        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        when(productoRepository.save(producto)).thenReturn(updatedProducto);
        when(productoMapper.toDto(updatedProducto)).thenReturn(resultDto);

        // Act
        ProductoUpdateDto result = productoService.update(id, dto);

        // Assert
        assertNotNull(result);
        assertEquals(resultDto, result);
        verify(productoRepository).findById(id);
        verify(productoMapper).updateFromDto(dto, producto);
        verify(productoRepository).save(producto);
        verify(productoMapper).toDto(updatedProducto);
        verifyNoInteractions(categoriaRepository);
    }

    @Test
    void update_WhenProductoNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        Integer id = 1;
        ProductoUpdateDto dto = new ProductoUpdateDto();
        when(productoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productoService.update(id, dto));
        verify(productoRepository).findById(id);
        verifyNoMoreInteractions(productoRepository, categoriaRepository, productoMapper);
    }

    @Test
    void update_WhenNewCategoriaNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        Integer id = 1;
        Integer newCategoriaId = 2;
        ProductoUpdateDto dto = new ProductoUpdateDto();
        dto.setCategoriaId(newCategoriaId);

        Producto producto = new Producto();
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        when(categoriaRepository.findById(newCategoriaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productoService.update(id, dto));
        verify(productoRepository).findById(id);
        verify(categoriaRepository).findById(newCategoriaId);
        verifyNoMoreInteractions(productoRepository, categoriaRepository);
    }

    @Test
    void delete_WhenProductoExists_ShouldDelete() {
        // Arrange
        Integer id = 1;
        when(productoRepository.existsById(id)).thenReturn(true);

        // Act
        productoService.delete(id);

        // Assert
        verify(productoRepository).existsById(id);
        verify(productoRepository).deleteById(id);
    }

    @Test
    void delete_WhenProductoNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        Integer id = 1;
        when(productoRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productoService.delete(id));
        verify(productoRepository).existsById(id);
        verifyNoMoreInteractions(productoRepository);
    }
}