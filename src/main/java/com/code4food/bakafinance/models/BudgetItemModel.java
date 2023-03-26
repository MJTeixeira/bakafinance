package com.code4food.bakafinance.models;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "BudgetItem")
public class BudgetItemModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(nullable = false, unique = false, length = 250, name = "Name")
    private String name;

    @Column(nullable = true, unique = false, length = 250, name = "Description")
    private String description;

    @Column(nullable = true, unique = false, name = "PredictionValue")
    private Double predictionValue;

    @Column(nullable = true, unique = false, name = "FinalValue")
    private Double finalValue;

    @Column(nullable = false, name = "PredictionDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate predictionDate;

    @Column(nullable = true, name = "FinalValueDate")
    private LocalDate finalValueDate;

    @Column(nullable = false, unique = false, length = 20, name = "PredictionMonth")
    private String predictionMonth;

    @Column(length = 1, name = "IsActive", nullable = false, columnDefinition = "int default 0")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive = false;

    @Column(nullable = false, unique = false, length = 20, name = "Status")
    private String status;

    @Column(length = 23, name = "CreateDate" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(length = 23, name = "UpdateDate" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updateDate = LocalDateTime.now();

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CategoryId", referencedColumnName = "Id")
    @JsonBackReference
    private BudgetItemCategoryModel budgetItemCategory;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TypeId", referencedColumnName = "Id")
    @JsonBackReference
    private BudgetItemTypeModel budgetItemType;


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

    public Double getPredictionValue() {
        return predictionValue;
    }

    public void setPredictionValue(Double predictionValue) {
        this.predictionValue = predictionValue;
    }

    public Double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Double finalValue) {
        this.finalValue = finalValue;
    }

    public LocalDate getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(LocalDate predictionDate) {
        this.predictionDate = predictionDate;
    }

    public LocalDate getFinalValueDate() {
        return finalValueDate;
    }

    public void setFinalValueDate(LocalDate finalValueDate) {
        this.finalValueDate = finalValueDate;
    }

    public String getPredictionMonth() {
        return predictionMonth;
    }

    public void setPredictionMonth(String predictionMonth) {
        this.predictionMonth = predictionMonth;
    }

    public Boolean GetIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
    public BudgetItemTypeModel getBudgetItemType() {
        return budgetItemType;
    }

    public void setBudgetItemType(BudgetItemTypeModel budgetItemType) {
        this.budgetItemType = budgetItemType;
    }

    public BudgetItemCategoryModel getBudgetItemCategory() {
        return budgetItemCategory;
    }

    public void setBudgetItemCategory(BudgetItemCategoryModel budgetItemCategory) {
        this.budgetItemCategory = budgetItemCategory;
    }
}
