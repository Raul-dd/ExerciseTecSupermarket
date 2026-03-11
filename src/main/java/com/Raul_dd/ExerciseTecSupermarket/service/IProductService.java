package com.Raul_dd.ExerciseTecSupermarket.service;

import com.Raul_dd.ExerciseTecSupermarket.dto.BranchDTO;
import com.Raul_dd.ExerciseTecSupermarket.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getProduct();
    ProductDTO createdProduct(ProductDTO productDto);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);

}
