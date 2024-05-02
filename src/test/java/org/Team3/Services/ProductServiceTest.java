package org.Team3.Services;

import org.Team3.Entities.Product;
import org.Team3.Repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldReturnAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnProductById() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void shouldReturnNullWhenProductNotFoundById() {
        when(productRepository.findById(1123123L)).thenReturn(Optional.empty());

        Product foundProduct = productService.getProductById(1123123L);

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById(1123123L);
    }

    @Test
    void shouldCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);

        productService.createProduct(product);

        verify(productRepository, times(1)).save(productCaptor.capture());
        Product capturedProduct = productCaptor.getValue();

        // Add assertions to check the properties of the captured Product object
         assertEquals("Test Product", capturedProduct.getName());
    }


    @Test
    void shouldUpdateProduct() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.updateProduct(1L, product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldNotUpdateProductWhenNotFound() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.updateProduct(1L, product);

        verify(productRepository, times(0)).save(product);
    }


    void shouldDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = productService.deleteProduct(1L);

        assertTrue(isDeleted);
        verify(productRepository, times(1)).deleteById(1L);
    }


    @Test
    void shouldNotDeleteProductWhenNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        boolean isDeleted = productService.deleteProduct(1L);

        assertFalse(isDeleted);
        verify(productRepository, times(0)).deleteById(1L);
    }

    @Test
    void shouldReturnAllProductPrices() {
        Product product1 = new Product();
        product1.setSellingPrice(100.0);
        Product product2 = new Product();
        product2.setSellingPrice(200.0);
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Double> prices = productService.getAllProductPrices();

        assertEquals(2, prices.size());
        assertTrue(prices.contains(100.0));
        assertTrue(prices.contains(200.0));
    }

    @Test
    void shouldReturnExpiredProducts() {
        Product product = new Product();
        product.setExpiryDate(LocalDate.now().minusDays(1));
        when(productRepository.findExpiredProducts(any(LocalDate.class))).thenReturn(Arrays.asList(product));

        List<Product> products = productService.getExpiredProducts(LocalDate.now());

        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @Test
    void shouldReturnLowStockProducts() {
        Product product = new Product();
        product.setCurrentStockLevel(5);
        when(productRepository.findLowStockProducts()).thenReturn(List.of(product));

        List<Product> products = productService.getLowStockProducts();

        assertEquals(1, products.size());
    }
}