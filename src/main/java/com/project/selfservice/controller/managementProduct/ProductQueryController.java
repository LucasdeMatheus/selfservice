package com.project.selfservice.controller.managementProduct;

import com.project.selfservice.domain.product.Category;
import com.project.selfservice.domain.product.Product;
import com.project.selfservice.domain.product.ProductDTO;
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
    public ResponseEntity<List<ProductDTO>> listProducts(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String category,
                                                         Pageable pageable) {
        Page<ProductDTO> productsDTO = null;

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
            productsDTO = productRepository.searchByNameAndCategory(name, categoryEnum, pageable);
        } else if (name != null) {
            // Filtra apenas por nome
            productsDTO = productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (categoryEnum != null) {
            // Filtra apenas por categoria
            productsDTO = productRepository.findByCategory(categoryEnum, pageable);
        } else {
            // Retorna todos os produtos
            Page<Product> products = productRepository.findAll(pageable);
            Page<ProductDTO> productDTO = products.map(product ->
                    new ProductDTO(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getAvailable(),
                            product.getCategory(),
                            product.getStock() != null ? product.getStock().getId() : null // Pegando apenas o ID do estoque
                    )
            );
        }

        return ResponseEntity.ok(productsDTO.getContent());
    }
}
