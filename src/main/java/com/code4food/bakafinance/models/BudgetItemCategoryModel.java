package com.code4food.bakafinance.models;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "BudgetItemCategory")
public class BudgetItemCategoryModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(nullable = false, unique = false, length = 250, name = "Name")
    private String name;

    @Column(nullable = true, unique = false, length = 250, name = "Description")
    private String description;

    @Column(nullable = false, unique = false, length = 20, name = "Status")
    private String status;

    @Column(nullable = false, length = 23, name = "CreateDate" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(length = 23, name = "UpdateDate" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updateDate = LocalDateTime.now();

    @OneToMany(mappedBy = "budgetItemCategory")
    @JsonManagedReference
    private Set<BudgetItemModel> budgetItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    //References
    public Set<BudgetItemModel> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(Set<BudgetItemModel> budgetItems) {
        this.budgetItems = budgetItems;
    }
}
