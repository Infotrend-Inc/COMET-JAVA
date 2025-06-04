package com.example.app.integration;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.app.model.Product;
import com.example.app.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setup() {
        when(productService.getAllProducts()).thenReturn(List.of(
            new Product(1L, "Test Product", "Test Description")
        ));
    }

    @Test
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));
    }

    @Test
    void testCreateProduct() throws Exception {
        Product newProduct = new Product(null, "New Product", "New Description");

        when(productService.createProduct(any(Product.class))).thenReturn(new Product(2L, "New Product", "New Description"));

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)  // Set content type to application/json
                .content(new ObjectMapper().writeValueAsString(newProduct)))  // Serialize the object to JSON
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Product"))
                .andExpect(jsonPath("$.description").value("New Description"));
    }

    // @Test
    // void testUpdateProduct() throws Exception {
    //     Product updatedProduct = new Product(null, "Updated Product", "Updated Description");

    //     when(productService.updateProduct(1L, updatedProduct)).thenReturn(new Product(1L, "Updated Product", "Updated Description"));

    //     mockMvc.perform(put("/api/products/1")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(new ObjectMapper().writeValueAsString(updatedProduct)))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.name").value("Updated Product"))
    //             .andExpect(jsonPath("$.description").value("Updated Description"));
    // }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}
