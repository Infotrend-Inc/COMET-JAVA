package com.example.app.service;

import com.example.app.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

    private Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(Long id) {
        return products.get(id);
    }

    @Override
    public Product createProduct(Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (products.containsKey(id)) {
            product.setId(id);
            products.put(id, product);
            return product;
        }
        return null; 
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(id);
    }

}
