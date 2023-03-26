package com.code4food.bakafinance.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.code4food.bakafinance.dtos.BudgetItemDTO;
import com.code4food.bakafinance.models.BudgetItemModel;
import com.code4food.bakafinance.services.BudgetItemService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("budgetitem")
public class BudgetItemController {
    
    @Autowired
    BudgetItemService budgetItemService;
    
    @PostMapping
    public ResponseEntity<Object> saveBudgetItem(@RequestBody @Valid BudgetItemDTO budgetItemDTO) {
        
        if(budgetItemService.existsByName(budgetItemDTO.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Budget Item name is already in use!");
        }
        System.out.println(budgetItemDTO);
        var budgetItemModel = new BudgetItemModel();
        BeanUtils.copyProperties(budgetItemDTO, budgetItemModel);
        budgetItemModel.setCreateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetItemService.save(budgetItemModel));
    }

    @GetMapping
    public ResponseEntity<Page<BudgetItemModel>> getAllBudgetItems(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBudgetItem(@PathVariable(value = "id") Integer id){
        Optional<BudgetItemModel> budgetItemModelOptional = budgetItemService.findById(id);
        if (!budgetItemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget Item not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBudgetItem(@PathVariable(value = "id") Integer id){
        Optional<BudgetItemModel> budgetItemModelOptional = budgetItemService.findById(id);
        if (!budgetItemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget Item not found.");
        }
        budgetItemService.delete(budgetItemModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Budget Item deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBudgetItem(@PathVariable(value = "id") Integer id,
                                                    @RequestBody @Valid BudgetItemDTO budgetItemDTO){
        Optional<BudgetItemModel> budgetItemModelOptional = budgetItemService.findById(id);
        if (!budgetItemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget Item not found.");
        }
        var budgetItemModel = new BudgetItemModel();
        BeanUtils.copyProperties(budgetItemDTO, budgetItemModel);
        budgetItemModel.setId(budgetItemModelOptional.get().getId());
        budgetItemModel.setCreateDate(budgetItemModelOptional.get().getCreateDate());
        budgetItemModel.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemService.save(budgetItemModel));
    }
}
