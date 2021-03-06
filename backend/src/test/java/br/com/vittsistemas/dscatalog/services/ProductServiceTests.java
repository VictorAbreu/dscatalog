package br.com.vittsistemas.dscatalog.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.vittsistemas.dscatalog.dto.ProductDTO;
import br.com.vittsistemas.dscatalog.entities.Category;
import br.com.vittsistemas.dscatalog.entities.Product;
import br.com.vittsistemas.dscatalog.repositories.CategoryRepository;
import br.com.vittsistemas.dscatalog.repositories.ProductRepository;
import br.com.vittsistemas.dscatalog.services.exceptions.DatabaseException;
import br.com.vittsistemas.dscatalog.services.exceptions.ResourceNotFoundException;
import br.com.vittsistemas.dscatalog.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Mock
	private CategoryRepository categoryRepository;

	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private PageImpl<Product> page;
	private Product product;
	private Category category;
	private ProductDTO productDTO;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 4L;
		product = Factory.createProduct();
		category = Factory.createCategory();
		productDTO = Factory.createProductDTO();
		page = new PageImpl<>(List.of(product));

		Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);

		Mockito.when(repository.save(any())).thenReturn(product);

		Mockito.when(repository.getOne(existingId)).thenReturn(product);
		Mockito.when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

		Mockito.when(categoryRepository.getOne(existingId)).thenReturn(category);
		Mockito.when(categoryRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.find(any(), any(), any())).thenReturn(page);
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}
	
	@Test
	public void updateShoudThrowResourceNotFoundExceptionWhenIdDoesNotExistis() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {

			service.update(nonExistingId, productDTO);

		});
	}

	@Test
	public void updateShouldRetunrProductDTOWhenIdExists() {

		ProductDTO dto = service.update(existingId, productDTO);

		Assertions.assertNotNull(dto);

	}

	@Test
	public void findByIdShouldRetunrProductDTOWhenIdExists() {

		ProductDTO dto = service.findById(existingId);

		Assertions.assertNotNull(dto);

		Mockito.verify(repository, Mockito.times(1)).findById(existingId);

	}

	@Test
	public void findByIdShoudThrowResourceNotFoundExceptionWhenIdDoesNotExistis() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {

			service.findById(nonExistingId);

			Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);

		});
	}

	@Test
	public void findAllPagesShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<ProductDTO> result = service.findAllPaged(0L, "", pageable);

		Assertions.assertNotNull(result);

	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenIdDoesNotExistis() {

		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExistis() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExistis() {

		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
}
