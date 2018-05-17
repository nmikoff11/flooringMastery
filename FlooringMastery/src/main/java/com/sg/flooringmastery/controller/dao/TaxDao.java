/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dto.Tax;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TaxDao {
    
    List<Tax> getAllTaxRates() throws Exception;
    
    public double getTaxRate (String stateName)throws Exception;
}
