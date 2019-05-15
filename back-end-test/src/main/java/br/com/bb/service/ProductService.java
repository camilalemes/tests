package br.com.bb.service;

import br.com.bb.entity.Product;
import br.com.bb.exception.CategoryNotFoundException;
import br.com.bb.repository.CategoryRepository;
import br.com.bb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAllByCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Invalid category."));

        return productRepository.findAllByCategoryId(id);
    }
}
