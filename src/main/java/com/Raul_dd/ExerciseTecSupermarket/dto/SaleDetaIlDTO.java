package com.Raul_dd.ExerciseTecSupermarket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetaIlDTO {
    private Long id;
    private String nameProd;
    private Integer quantityProd;
    private Double price;
    private Double subtotal;
}
