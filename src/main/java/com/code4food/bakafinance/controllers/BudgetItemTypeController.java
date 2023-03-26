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

import com.code4food.bakafinance.dtos.BudgetItemTypeDTO;
import com.code4food.bakafinance.models.BudgetItemTypeModel;
import com.code4food.bakafinance.services.BudgetItemTypeService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("budgetitemtype")
public class BudgetItemTypeController {

    @Autowired
    BudgetItemTypeService budgetItemTypeService;

    @PostMapping
    public ResponseEntity<Object> saveType(@RequestBody @Valid BudgetItemTypeDTO budgetItemTypeDTO) {
        
        if(budgetItemTypeService.existsByName(budgetItemTypeDTO.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Type name is already in use!");
        }

        var budgetItemTypeModel = new BudgetItemTypeModel();
        BeanUtils.copyProperties(budgetItemTypeDTO, budgetItemTypeModel);
        budgetItemTypeModel.setCreateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetItemTypeService.save(budgetItemTypeModel));
    }

    @GetMapping
    public ResponseEntity<Page<BudgetItemTypeModel>> getAllTypes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemTypeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBudgetItemType(@PathVariable(value = "id") Integer id){
        Optional<BudgetItemTypeModel> budgetItemTypeModelOptional = budgetItemTypeService.findById(id);
        if (!budgetItemTypeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemTypeModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBudgetItemType(@PathVariable(value = "id") Integer id){
        Optional<BudgetItemTypeModel> budgetItemTypeModelOptional = budgetItemTypeService.findById(id);
        if (!budgetItemTypeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
        }
        budgetItemTypeService.delete(budgetItemTypeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Type deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateType(@PathVariable(value = "id") Integer id,
                                                    @RequestBody @Valid BudgetItemTypeDTO budgetItemTypeDTO){
        Optional<BudgetItemTypeModel> budgetItemTypeModelOptional = budgetItemTypeService.findById(id);
        if (!budgetItemTypeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
        }
        var budgetItemTypeModel = new BudgetItemTypeModel();
        BeanUtils.copyProperties(budgetItemTypeDTO, budgetItemTypeModel);
        budgetItemTypeModel.setId(budgetItemTypeModelOptional.get().getId());
        budgetItemTypeModel.setCreateDate(budgetItemTypeModelOptional.get().getCreateDate());
        budgetItemTypeModel.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(budgetItemTypeService.save(budgetItemTypeModel));
    }
}
