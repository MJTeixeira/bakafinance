package com.code4food.bakafinance.repositories;

import com.code4food.bakafinance.models.BudgetItemTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBudgetItemTypeRepository extends JpaRepository<BudgetItemTypeModel, Integer> {
    boolean existsByName(String name);
}
