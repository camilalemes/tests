package br.com.bb.controller;

import br.com.bb.entity.Product;
import br.com.bb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/listByCategory/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> findAllByCategory(@PathVariable("category_id") Long id) {
        return new ResponseEntity<>(productService.findAllByCategory(id), HttpStatus.OK);
    }
}
