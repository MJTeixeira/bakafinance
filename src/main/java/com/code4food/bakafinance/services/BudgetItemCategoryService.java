package com.code4food.bakafinance.services;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.code4food.bakafinance.models.BudgetItemCategoryModel;
import com.code4food.bakafinance.repositories.IBudgetItemCategoryRepository;

@Service
public class BudgetItemCategoryService {
    
    @Autowired
    IBudgetItemCategoryRepository iBudgetItemCategoryRepository;

    public boolean existsByName(String name) {
        return iBudgetItemCategoryRepository.existsByName(name);
    }

    public Page<BudgetItemCategoryModel> findAll(Pageable pageable) {
        return iBudgetItemCategoryRepository.findAll(pageable);
    }

    public Optional<BudgetItemCategoryModel> findById(Integer id) {
        return iBudgetItemCategoryRepository.findById(id);
    }

    @Transactional
    public BudgetItemCategoryModel save(BudgetItemCategoryModel budgetItemCategoryModel) {
        return iBudgetItemCategoryRepository.save(budgetItemCategoryModel);
    }

    @Transactional
    public void delete(BudgetItemCategoryModel budgetItemCategoryModel) {
        iBudgetItemCategoryRepository.delete(budgetItemCategoryModel);
    }
}
