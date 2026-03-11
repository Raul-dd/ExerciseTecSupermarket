package com.Raul_dd.ExerciseTecSupermarket.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {
    //datos de la venta
    private Long id;
    private LocalDate date;
    private String state;
    //datos de la sucursa
    private Long idBranch;
    //lista de detalles
    private List<SaleDetaIlDTO> detail;

    //total de la venta
    private Double total;
}
