/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dao.OrderDaoImpl;
import com.sg.flooringmastery.controller.dao.ProductDaoImpl;
import com.sg.flooringmastery.controller.dao.TaxDaoImpl;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class TrainingOrderDaoImpl extends OrderDaoImpl  {
    
    
    @Override
    public void writeOrder(LocalDate date) throws NotValidChoiceException, IOException{
        
    }
    
    
}
