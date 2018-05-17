/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {
    
    List<Order> getOrders(LocalDate date) throws Exception;
        
    public void addOrderToFile(Order order, LocalDate date) throws IOException, NotValidChoiceException;
    
    public Order getEditToOrder(int orderNumber, LocalDate orderDate) throws Exception, NotValidChoiceException;
    
    void removeOrder(LocalDate orderDate, int orderNumber) throws Exception, NotValidChoiceException ;
    }
