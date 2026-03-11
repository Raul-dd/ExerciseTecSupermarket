package com.Raul_dd.ExerciseTecSupermarket.service;

import com.Raul_dd.ExerciseTecSupermarket.dto.SaleDTO;
import java.util.List;

public interface ISaleService {

    List<SaleDTO> getSales();
    SaleDTO createdSale(SaleDTO saleDto);
    SaleDTO updateSale(Long id, SaleDTO saleDto);
    void deleteSale(Long id);

}
