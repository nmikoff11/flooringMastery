/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.service;

import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.dto.Product;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderProcessService {
    
    public Order orderTotals(Order order) throws Exception;     
    
    public void orderRemove(LocalDate date, int orderNumber) throws Exception;
    
    public Order getOrder(int orderDate, LocalDate date) throws Exception;
       
    List<Product> getProductList() throws Exception;       
    
    public void addOrder(Order order, LocalDate date) throws NotValidChoiceException, IOException;        
    
    List<Order> getAllOrders(LocalDate date) throws Exception;
        
    
  
}
