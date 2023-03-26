package com.code4food.bakafinance.services;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.code4food.bakafinance.models.BudgetItemModel;
import com.code4food.bakafinance.repositories.IBudgetItemRepository;

@Service
public class BudgetItemService {
    
    @Autowired
    IBudgetItemRepository iBudgetItemRepository;

    public boolean existsByName(String name) {
        return iBudgetItemRepository.existsByName(name);
    }

    public Page<BudgetItemModel> findAll(Pageable pageable) {
        return iBudgetItemRepository.findAll(pageable);
    }

    public Optional<BudgetItemModel> findById(Integer id) {
        return iBudgetItemRepository.findById(id);
    }

    @Transactional
    public BudgetItemModel save(BudgetItemModel budgetItemCategoryModel) {
        return iBudgetItemRepository.save(budgetItemCategoryModel);
    }

    @Transactional
    public void delete(BudgetItemModel budgetItemModel) {
        iBudgetItemRepository.delete(budgetItemModel);
    }
}
