package com.code4food.bakafinance.repositories;

import com.code4food.bakafinance.models.BudgetItemCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBudgetItemCategoryRepository extends JpaRepository<BudgetItemCategoryModel, Integer> {
    boolean existsByName(String name);
}
