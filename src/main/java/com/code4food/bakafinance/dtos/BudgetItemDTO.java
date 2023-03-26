package com.code4food.bakafinance.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BudgetItemDTO {

    @NotBlank
    @Size(max = 250)
    private String name;

    @Size(max = 250)
    private String description;

    private Double predictionValue;

    private Double finalValue;

    private LocalDate predictionDate;

    private LocalDate finalValueDate;

    private String predictionMonth;

    private Boolean isActive;
    
    @Size(max = 20)
    private String status;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer categoryId;

    private Integer typeId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
