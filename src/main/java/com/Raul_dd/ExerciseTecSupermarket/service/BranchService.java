package com.Raul_dd.ExerciseTecSupermarket.service;

import com.Raul_dd.ExerciseTecSupermarket.dto.BranchDTO;
import com.Raul_dd.ExerciseTecSupermarket.exception.NotFoundException;
import com.Raul_dd.ExerciseTecSupermarket.mapper.Mapper;
import com.Raul_dd.ExerciseTecSupermarket.model.Branch;
import com.Raul_dd.ExerciseTecSupermarket.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService{

    @Autowired
    private BranchRepository repo;

    @Override
    public List<BranchDTO> getBranch() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchDTO createdBranch(BranchDTO branchDto) {
        Branch branch = Branch.builder()
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .build();
        return Mapper.toDTO(repo.save(branch));
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) {
        //Validar si existe la sucursal
        Branch branch = repo.findById(id)
                .orElseThrow(()->new NotFoundException("Sucuarsal no encontrada"));

        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());

        return Mapper.toDTO(repo.save(branch));
    }

    @Override
    public void deleteBranch(Long id) {
        //Verificar si la sucursal existe
        if (!repo.existsById(id))
            throw new NotFoundException("No existe la sucursal");

        repo.deleteById(id);
    }
}
