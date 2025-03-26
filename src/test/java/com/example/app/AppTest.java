package com.example.app;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.app.controller.ProductController;
import com.example.app.model.Product;
import com.example.app.service.ProductService;

class AppTest{

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProducts() {
        List<Product> mockProducts = List.of(new Product(1L, "Test Product", "Test Description"));
        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Product", response.getBody().get(0).getName());
    }

    @Test
    void testCreateProduct() {
        Product newProduct = new Product(null, "New Product", "New Description");
        Product savedProduct = new Product(2L, "New Product", "New Description");

        when(productService.createProduct(newProduct)).thenReturn(savedProduct);

        ResponseEntity<Product> response = productController.createProduct(newProduct);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("New Product", response.getBody().getName());
        assertEquals("New Description", response.getBody().getDescription());
    }

    @Test
    void testDeleteProduct() {
        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(204, response.getStatusCodeValue());
    }
}
