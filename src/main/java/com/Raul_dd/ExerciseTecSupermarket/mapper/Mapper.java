package com.Raul_dd.ExerciseTecSupermarket.mapper;

import com.Raul_dd.ExerciseTecSupermarket.dto.BranchDTO;
import com.Raul_dd.ExerciseTecSupermarket.dto.ProductDTO;
import com.Raul_dd.ExerciseTecSupermarket.dto.SaleDTO;
import com.Raul_dd.ExerciseTecSupermarket.dto.SaleDetaIlDTO;
import com.Raul_dd.ExerciseTecSupermarket.model.Branch;
import com.Raul_dd.ExerciseTecSupermarket.model.Product;
import com.Raul_dd.ExerciseTecSupermarket.model.Sale;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de Producto a ProductoDTO
    public static ProductDTO toDTO(Product p){
        if (p == null) return null;

        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .price(p.getPrice())
                .quantity(p.getQuantity())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static SaleDTO toDTO(Sale sale){
        if (sale == null) return null;

        var detail = sale.getDetail().stream().map(det ->
                SaleDetaIlDTO.builder()
                        .id(det.getProduct().getId())
                        .nameProd(det.getProduct().getName())
                        .quantityProd(det.getQuantity())
                        .price(det.getUnitPrice())
                        .subtotal(det.getUnitPrice() * det.getQuantity())
                        .build()
        ).collect(Collectors.toList());

        var total = detail.stream()
                .map(SaleDetaIlDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return SaleDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .idBranch(sale.getBranch().getId())
                .state(sale.getState())
                .detail(detail)
                .total(total)
                .build();
    }


    //Mapeo de Sucursal a SucursalDTO
    public static BranchDTO toDTO(Branch b){
        if (b == null) return null;

        return BranchDTO.builder()
                .id(b.getId())
                .name(b.getName())
                .address(b.getAddress())
                .build();
    }
}
