package com.Raul_dd.ExerciseTecSupermarket.service;

import com.Raul_dd.ExerciseTecSupermarket.dto.BranchDTO;
import com.Raul_dd.ExerciseTecSupermarket.model.Branch;

import java.util.List;

public interface IBranchService {

    List<BranchDTO> getBranch();
    BranchDTO createdBranch(BranchDTO branchDto);
    BranchDTO updateBranch(Long id, BranchDTO branchDTO);
    void deleteBranch(Long id);

}
