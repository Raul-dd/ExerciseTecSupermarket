package com.Raul_dd.ExerciseTecSupermarket.controller;

import com.Raul_dd.ExerciseTecSupermarket.dto.BranchDTO;
import com.Raul_dd.ExerciseTecSupermarket.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getBranchs(){
        return ResponseEntity.ok(branchService.getBranch());
    }

    @PostMapping
    public ResponseEntity<BranchDTO> postBranch(@RequestBody BranchDTO branchDTO){
        BranchDTO created = branchService.createdBranch(branchDTO);

        return ResponseEntity.created(URI.create("/api/branch" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id,
                                               @RequestBody BranchDTO branchDTO){
        return ResponseEntity.ok(branchService.updateBranch(id,branchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id){
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }

}




