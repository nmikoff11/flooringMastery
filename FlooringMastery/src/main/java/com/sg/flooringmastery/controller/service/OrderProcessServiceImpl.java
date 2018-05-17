/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.service;

import com.sg.flooringmastery.controller.dao.OrderDao;
import com.sg.flooringmastery.controller.dao.OrderDaoImpl;
import com.sg.flooringmastery.controller.dao.ProductDaoImpl;
import com.sg.flooringmastery.controller.dao.TaxDaoImpl;
import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.dto.Product;
import com.sg.flooringmastery.controller.dto.Tax;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderProcessServiceImpl implements OrderProcessService {

    ProductDaoImpl productDao = new ProductDaoImpl();
    TaxDaoImpl taxDao = new TaxDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
           
    public OrderProcessServiceImpl(ProductDaoImpl productDao, TaxDaoImpl taxDao, 
            OrderDaoImpl orderDao){
     this.productDao = productDao;
     this.taxDao = taxDao;
     this.orderDao = orderDao;
    }    
    @Override
    public Order orderTotals(Order order) throws Exception, FileNotFoundException {
    
            
    BigDecimal standardLaborCost = standardlaborCost();
    BigDecimal laborCost = BigDecimal.valueOf(order.getSizeOfArea()).multiply(standardLaborCost);
    BigDecimal materialCost = productCost(order.getProductType(),order.getSizeOfArea());
    BigDecimal costBeforeTax = laborCost.add(materialCost);
    BigDecimal taxRate = getTaxRate(order.getStateName());
    BigDecimal taxTotal = costBeforeTax.multiply(taxRate);
    BigDecimal totalCostForProject = taxTotal.add(costBeforeTax);
    //price per square ft to do project including all costs.
    BigDecimal totalCostPerSqFt = totalCostForProject.divide
                (BigDecimal.valueOf(order.getSizeOfArea()));
    //converting to doubles because I initially made everything doubles. 
    order.setOrderDate(order.getOrderDate());
    order.setStandardLabor(standardLaborCost.doubleValue()); 
    order.setLaborCost(laborCost.doubleValue());
    order.setMaterialCost(materialCost.doubleValue());
    order.setTaxRate(taxRate.doubleValue());
    order.setTaxTotal(taxTotal.doubleValue());
    order.setOrderTotal(totalCostForProject.doubleValue());
    order.setCostPerSqFt(totalCostPerSqFt.doubleValue());
    
    
    try {
            List<Order> orders = orderDao.getOrders(order.getOrderDate());
            int orderNumber = 0;
            for(Order o : orders){
                int i= Math.max(o.getOrderNumber(), orders.size());
                if(i > orders.size()){
                    orderNumber = o.getOrderNumber() + 1;
                    order.setOrderNumber(orderNumber);
                }
                else{
                    orderNumber = orders.size() + 1;
                    order.setOrderNumber(orderNumber);
                }
                    
            }            
            return order;
        } catch (FileNotFoundException e) {
            order.setOrderNumber(1);
            return order;
        }           
        
    }
    

    //standard cost p/sqFT labor
    public BigDecimal standardlaborCost() {
        BigDecimal laborCostPerSqFt = new BigDecimal(2.99);
        return laborCostPerSqFt;
    }

    //calculating material cost for by per unit cost and then size of area. 
    public BigDecimal productCost(String productName, Double area) throws Exception, NotValidChoiceException {
        List<Product> products = productDao.getAllProducts();
        BigDecimal totalProductCost = new BigDecimal(0);
        boolean isProduct = false;
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(productName)) {
                totalProductCost = BigDecimal.valueOf(p.getSqFtCost())
                        .multiply(BigDecimal.valueOf(area));
                        isProduct = true;
                }
        }
        if (!isProduct){
            throw new NotValidChoiceException("The product you entered is not currently carried");
            
        }        
        return totalProductCost;
    }

    public BigDecimal getTaxRate(String stateName) throws Exception {
        List<Tax> taxes = taxDao.getAllTaxRates();
        BigDecimal taxRate = new BigDecimal(.01);
        boolean isState = false;
        for (Tax t : taxes) {
            if (t.getStateName().equalsIgnoreCase(stateName)) {
                taxRate = BigDecimal.valueOf(t.getTaxRate());
                isState = true;
            }
        }
        if (!isState){
            throw new NotValidChoiceException("Not valid valid state name");
        }
        //converting tax rate to a percentage type value.
        return taxRate.divide(new BigDecimal("100"));
    }
    
    @Override
    public void orderRemove(LocalDate date, int orderNumber) throws Exception{
        orderDao.removeOrder(date, orderNumber);
    }
    @Override
    public Order getOrder(int orderDate, LocalDate date) throws Exception{
        return orderDao.getEditToOrder(orderDate, date);
    }
    @Override
    public List<Product> getProductList() throws Exception{
        return productDao.getAllProducts();
    }
    @Override
    public void addOrder(Order order, LocalDate date) throws NotValidChoiceException, IOException{
        orderDao.addOrderToFile(order, date);
    }
    @Override
    public List<Order> getAllOrders(LocalDate date) throws Exception{
        return orderDao.getOrders(date);
    }
            
 }
