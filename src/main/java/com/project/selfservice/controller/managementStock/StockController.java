package com.project.selfservice.controller.managementStock;


import com.project.selfservice.domain.product.Product;
import com.project.selfservice.domain.product.ProductRepository;
import com.project.selfservice.domain.stock.ProductStatus;
import com.project.selfservice.domain.stock.Stock;
import com.project.selfservice.domain.stock.StockDataProduct;
import com.project.selfservice.domain.stock.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add/{id}")
    public ResponseEntity<?> addStock(@RequestBody StockDataProduct stockDataProduct, @PathVariable("id") Long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setQuantityInStock(stockDataProduct.quantityInStock());
        stock.setCostPrice(stockDataProduct.costPrice());
        stock.setStatus(stockDataProduct.status());
        stock.setMaximumQuantity(stockDataProduct.maximumQuantity());
        stock.setSellingPrice(product.getPrice());
        stock.setMinimumQuantity(stockDataProduct.minimumQuantity());

        stockRepository.save(stock);

        return ResponseEntity.status(HttpStatus.CREATED).body(stock);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStock(@RequestBody StockDataProduct stockDataProduct, @PathVariable("id") Long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));

        stock.setQuantityInStock(stock.getQuantityInStock() + stockDataProduct.quantityInStock());
        stock.setCostPrice(stockDataProduct.costPrice());
        stock.setStatus(stockDataProduct.status());
        stock.setMaximumQuantity(stockDataProduct.maximumQuantity());
        stock.setSellingPrice(stock.getProduct().getPrice());
        stock.setMinimumQuantity(stockDataProduct.minimumQuantity());

        stockRepository.save(stock);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/status/{id}/{STATUS}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @PathVariable("STATUS") ProductStatus status) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));

        stock.setStatus(status);
        stockRepository.save(stock);
        return ResponseEntity.ok(true);
    }

}
