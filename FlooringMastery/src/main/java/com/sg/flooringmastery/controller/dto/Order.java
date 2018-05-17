
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dto;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author apprentice
 */
public class Order {
    private int orderNumber = 0;
    private LocalDate orderDate;
    private double sizeOfArea = 0;
    private String productType = "";
    private double taxRate = 0 ;
    private double costPerSqFt = 0;
    private double materialCost = 0;
    private double taxTotal = 0;
    private double orderTotal = 0;
    private String stateName = "";
    private double laborCost = 0;
    private double standardLabor = 0;
    private String customerName = "";

    public double getStandardLabor() {
        return standardLabor;
    }

    public void setStandardLabor(double standardLabor) {
        this.standardLabor = standardLabor;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    
    
    public double getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(double costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(double materialCost) {
        this.materialCost = materialCost;
    }

    public double getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
    

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getSizeOfArea() {
        return sizeOfArea;
    }

    public void setSizeOfArea(double sizeOfArea) {
        this.sizeOfArea = sizeOfArea;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
