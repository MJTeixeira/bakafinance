package com.code4food.bakafinance.services;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.code4food.bakafinance.models.BudgetItemTypeModel;
import com.code4food.bakafinance.repositories.IBudgetItemTypeRepository;

@Service
public class BudgetItemTypeService {

    @Autowired
    IBudgetItemTypeRepository iBudgetItemTypeRepository;

    public boolean existsByName(String name) {
        return iBudgetItemTypeRepository.existsByName(name);
    }

    public Page<BudgetItemTypeModel> findAll(Pageable pageable) {
        return iBudgetItemTypeRepository.findAll(pageable);
    }

    public Optional<BudgetItemTypeModel> findById(Integer id) {
        return iBudgetItemTypeRepository.findById(id);
    }

    @Transactional
    public BudgetItemTypeModel save(BudgetItemTypeModel budgetItemTypeModel) {
        return iBudgetItemTypeRepository.save(budgetItemTypeModel);
    }

    @Transactional
    public void delete(BudgetItemTypeModel budgetItemTypeModel) {
        iBudgetItemTypeRepository.delete(budgetItemTypeModel);
    }
}
