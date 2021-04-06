package com.euchigere.exercise4.services.impl;

import com.euchigere.exercise4.models.Product;
import com.euchigere.exercise4.repository.ProductRepository;
import com.euchigere.exercise4.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
