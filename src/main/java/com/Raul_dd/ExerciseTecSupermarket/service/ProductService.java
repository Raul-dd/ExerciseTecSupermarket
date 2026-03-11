package com.Raul_dd.ExerciseTecSupermarket.service;

import com.Raul_dd.ExerciseTecSupermarket.dto.ProductDTO;
import com.Raul_dd.ExerciseTecSupermarket.exception.NotFoundException;
import com.Raul_dd.ExerciseTecSupermarket.mapper.Mapper;
import com.Raul_dd.ExerciseTecSupermarket.model.Product;
import com.Raul_dd.ExerciseTecSupermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> getProduct() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO createdProduct(ProductDTO productDto) {
        var prod = Product.builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        //Verificar si el producto existe
        Product prod = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        prod.setName(productDTO.getName());
        prod.setCategory(productDTO.getCategory());
        prod.setQuantity(productDTO.getQuantity());
        prod.setPrice(productDTO.getPrice());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!repo.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }

        repo.deleteById(id);
    }
}
