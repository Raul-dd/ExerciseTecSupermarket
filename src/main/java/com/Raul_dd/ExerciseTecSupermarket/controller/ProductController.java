package com.Raul_dd.ExerciseTecSupermarket.controller;

import com.Raul_dd.ExerciseTecSupermarket.dto.ProductDTO;
import com.Raul_dd.ExerciseTecSupermarket.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.getProduct());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createdProduct(@RequestBody ProductDTO productDTO){
        ProductDTO created = productService.createdProduct(productDTO);

        return ResponseEntity.created(URI.create("/api/products" + created.getId())).body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
