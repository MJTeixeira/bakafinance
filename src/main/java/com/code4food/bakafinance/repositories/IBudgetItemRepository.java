package com.code4food.bakafinance.repositories;

import com.code4food.bakafinance.models.BudgetItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBudgetItemRepository extends JpaRepository<BudgetItemModel, Integer> {
    boolean existsByName(String name);
}
