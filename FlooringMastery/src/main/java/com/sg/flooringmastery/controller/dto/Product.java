/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dto;

/**
 *
 * @author apprentice
 */
public class Product {
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSqFtCost() {
        return sqFtCost;
    }

    public void setSqFtCost(double sqFtCost) {
        this.sqFtCost = sqFtCost;
    }
    private double sqFtCost;
    
    
}
