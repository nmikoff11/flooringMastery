/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dto.Product;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ProductDao {
    
    List<Product> getAllProducts() throws Exception;
        
    public Product getProduct(String productName) throws NotValidChoiceException;
}
