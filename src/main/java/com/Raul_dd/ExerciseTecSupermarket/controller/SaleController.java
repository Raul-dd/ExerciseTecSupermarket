package com.Raul_dd.ExerciseTecSupermarket.controller;

import com.Raul_dd.ExerciseTecSupermarket.dto.SaleDTO;
import com.Raul_dd.ExerciseTecSupermarket.model.Sale;
import com.Raul_dd.ExerciseTecSupermarket.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getSales(){
        return ResponseEntity.ok(saleService.getSales());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createdSale(@RequestBody SaleDTO saleDTO){
        SaleDTO created = saleService.createdSale(saleDTO);

        return ResponseEntity.created(URI.create("/api/sales/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO){
        return ResponseEntity.ok(saleService.updateSale(id, saleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

}









