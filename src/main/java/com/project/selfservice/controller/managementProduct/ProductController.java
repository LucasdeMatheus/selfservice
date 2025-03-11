package com.project.selfservice.controller.managementProduct;

import com.project.selfservice.domain.product.DataProduct;
import com.project.selfservice.domain.product.Product;
import com.project.selfservice.domain.product.ProductRepository;
import com.project.selfservice.domain.stock.ProductStatus;
import com.project.selfservice.domain.stock.Stock;
import com.project.selfservice.domain.stock.StockRepository;
import com.project.selfservice.domain.user.User;
import com.project.selfservice.domain.user.authentication.AuthenticationService;
import com.project.selfservice.domain.user.authentication.DataAuthentication;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/products/manage")
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;


    @PostMapping("/add")
    public ResponseEntity<?> registerProduct(@RequestBody @Valid DataProduct dataProduct) {
        // Verificação do ADMIN
        if (!authenticationService.isAdmin(dataProduct.user())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado.");
        }

        // Criar e salvar o produto primeiro
        Product product = new Product();
        product.setName(dataProduct.name());
        product.setAvailable(dataProduct.available());
        product.setCategory(dataProduct.category());
        product.setDescription(dataProduct.description());
        product.setPrice(dataProduct.price());

        product = productRepository.save(product);

        // Criar o estoque associado ao produto salvo
        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setQuantityInStock(0);
        stock.setCostPrice(BigDecimal.ZERO);
        stock.setStatus(ProductStatus.INATIVO);
        stock.setMaximumQuantity(0);
        stock.setSellingPrice(product.getPrice());
        stock.setMinimumQuantity(0);

        stock = stockRepository.save(stock); // Salva o estoque

        // Atualiza o produto com o estoque salvo
        product.setStock(stock);
        productRepository.save(product); // Atualiza o produto com a referência ao estoque

        return ResponseEntity.ok("Produto cadastrado com estoque inicial criado.");
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestBody @Valid DataProduct dataProduct){
        // verificação do ADMIN
        if (!authenticationService.isAdmin(dataProduct.user())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado.");
        }
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Produto não encontrado");
        }

        Product p = product.get();
        p.setName(dataProduct.name());
        p.setAvailable(dataProduct.available());
        p.setCategory(dataProduct.category());
        p.setDescription(dataProduct.description());
        p.setPrice(dataProduct.price());

        p.getStock().setSellingPrice(p.getPrice());

        productRepository.save(p);


        return ResponseEntity.ok("produto editado");
    }


}
