package com.project.selfservice.controller.managementProduct;

import com.project.selfservice.domain.product.Category;
import com.project.selfservice.domain.product.Product;
import com.project.selfservice.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list-products")
public class ProductQueryController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(@RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String category,
                                                      Pageable pageable) {
        Page<Product> products;

        Category categoryEnum = null;
        if (category != null) {
            try {
                categoryEnum = Category.valueOf(category.toUpperCase());  // Converte a string para o enum
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(List.of());  // Se a categoria for inválida
            }
        }

        if (name != null && categoryEnum != null) {
            // Filtra por nome e categoria
            products = productRepository.searchByNameAndCategory(name, categoryEnum, pageable);
        } else if (name != null) {
            // Filtra apenas por nome
            products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (categoryEnum != null) {
            // Filtra apenas por categoria
            products = productRepository.findByCategory(categoryEnum, pageable);
        } else {
            // Retorna todos os produtos
            products = productRepository.findAll(pageable);
        }

        return ResponseEntity.ok(products.getContent());
    }
}
