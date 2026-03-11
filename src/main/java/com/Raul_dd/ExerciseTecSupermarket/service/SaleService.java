package com.Raul_dd.ExerciseTecSupermarket.service;

import com.Raul_dd.ExerciseTecSupermarket.dto.SaleDTO;
import com.Raul_dd.ExerciseTecSupermarket.dto.SaleDetaIlDTO;
import com.Raul_dd.ExerciseTecSupermarket.exception.NotFoundException;
import com.Raul_dd.ExerciseTecSupermarket.mapper.Mapper;
import com.Raul_dd.ExerciseTecSupermarket.model.Branch;
import com.Raul_dd.ExerciseTecSupermarket.model.Product;
import com.Raul_dd.ExerciseTecSupermarket.model.Sale;
import com.Raul_dd.ExerciseTecSupermarket.model.SaleDetail;
import com.Raul_dd.ExerciseTecSupermarket.repository.BranchRepository;
import com.Raul_dd.ExerciseTecSupermarket.repository.ProductRepository;
import com.Raul_dd.ExerciseTecSupermarket.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<SaleDTO> getSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleDTO> salesDto = new ArrayList<>();
        SaleDTO dto;
        for (Sale s : sales){
            dto = Mapper.toDTO(s);
            salesDto.add(dto);
        }
        return salesDto;
    }

    @Override
    public SaleDTO createdSale(SaleDTO saleDto) {
        //Validaciones
        if (saleDto == null) throw new NotFoundException("VentaDTO es null");
        if (saleDto.getIdBranch() == null) throw new RuntimeException("Debe incluir la sucursal");
        if (saleDto.getDetail() == null || saleDto.getDetail().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Branch branch = branchRepository.findById(saleDto.getIdBranch()).orElse(null);
        if (branch == null){
            throw new NotFoundException("Sucuesal no encontrada");
        }
        //Crear la venta
        Sale sale = new Sale();
        sale.setDate(saleDto.getDate());
        sale.setState(saleDto.getState());
        sale.setBranch(branch);
        sale.setTotal(saleDto.getTotal());

        //La lista de detalles
        //Aqui estan los productos
        List<SaleDetail> details = new ArrayList<>();
        Double totalCalculate = 0.0;

        for (SaleDetaIlDTO detaIlDTO : saleDto.getDetail()){
            //buscar producto por id
            Product p = productRepository.findByName(detaIlDTO.getNameProd()).orElse(null);
            if (p == null) {
                throw new RuntimeException("Producto no encontrado: " + detaIlDTO.getNameProd());
            }


        //Crear detalle
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setProduct(p);
        saleDetail.setUnitPrice(detaIlDTO.getPrice());
        saleDetail.setQuantity(detaIlDTO.getQuantityProd());
        saleDetail.setSale(sale);

        details.add(saleDetail);
        totalCalculate = totalCalculate+(detaIlDTO.getPrice()*detaIlDTO.getQuantityProd());

        }
        //sETEAMOS LA LISTA DE DETALLE VENTA
        sale.setDetail(details);

        //Guardamos en la BD
        sale = saleRepository.save(sale);

        //mapeo de salida
        SaleDTO exitSale = Mapper.toDTO(sale);

        return exitSale;

    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDto) {
        //Buscar si la venta existe par aactualizarla
        Sale s = saleRepository.findById(id).orElse(null);
        if (s == null) throw new RuntimeException("Venta no encontrada");

        if (saleDto.getDate() != null){
            s.setDate(saleDto.getDate());
        }
        if (saleDto.getState() != null){
            s.setState(saleDto.getState());
        }

        if (saleDto.getTotal() != null){
            s.setTotal(saleDto.getTotal());
        }

        if (saleDto.getIdBranch() != null){
            Branch branch = branchRepository.findById(saleDto.getIdBranch()).orElse(null);
            if (branch == null) throw new NotFoundException("Sucursal no encontrada");
            s.setBranch(branch);
        }
        saleRepository.save(s);

        SaleDTO exitSale = Mapper.toDTO(s);

        return exitSale;
    }

    @Override
    public void deleteSale(Long id) {

        Sale s = saleRepository.findById(id).orElse(null);
        if (s == null) throw new RuntimeException("Venta no encontrada");
        saleRepository.delete(s);
    }
}
