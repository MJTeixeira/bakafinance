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

import com.code4food.bakafinance.dtos.BudgetItemCategoryDTO;
import com.code4food.bakafinance.models.BudgetItemCategoryModel;
import com.code4food.bakafinance.services.BudgetItemCategoryService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("budgetitemcategory")
public class BudgetItemCategoryController {
    
    @Autowired
    BudgetItemCategoryService budgetItemCategoryService;

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid BudgetItemCategoryDTO budgetItemCategoryDTO){
        
        if(budgetItemCategoryService.existsByName(budgetItemCategoryDTO.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Category name is already in use!");
        }

        var budgetItemCategoryModel = new BudgetItemCategoryModel();
        BeanUtils.copyProperties(budgetItemCategoryDTO, budgetItemCategoryModel);
        budgetItemCategoryModel.setCreateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetItemCategoryService.save(budgetItemCategoryModel));
    }

    @GetMapping
    public ResponseEntity<Page<BudgetItemCategoryModel>> getAllCategories(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemCategoryService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBudgetItemCategory(@PathVariable(value = "id") Integer id){
        Optional<BudgetItemCategoryModel> budgetItemCategoryModelOptional = budgetItemCategoryService.findById(id);
        if (!budgetItemCategoryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemCategoryModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBudgetItemCategory(@PathVariable(value = "id") Integer id){
        Optional<BudgetItemCategoryModel> budgetItemCategoryModelOptional = budgetItemCategoryService.findById(id);
        if (!budgetItemCategoryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        budgetItemCategoryService.delete(budgetItemCategoryModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateType(@PathVariable(value = "id") Integer id,
                                                    @RequestBody @Valid BudgetItemCategoryDTO budgetItemCategoryDTO){
        Optional<BudgetItemCategoryModel> budgetItemCategoryModelOptional = budgetItemCategoryService.findById(id);
        if (!budgetItemCategoryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        var budgetItemCategoryModel = new BudgetItemCategoryModel();
        BeanUtils.copyProperties(budgetItemCategoryDTO, budgetItemCategoryModel);
        budgetItemCategoryModel.setId(budgetItemCategoryModelOptional.get().getId());
        budgetItemCategoryModel.setCreateDate(budgetItemCategoryModelOptional.get().getCreateDate());
        budgetItemCategoryModel.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemCategoryService.save(budgetItemCategoryModel));
    }
}
