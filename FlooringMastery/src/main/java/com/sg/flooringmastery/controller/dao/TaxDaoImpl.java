/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;


import com.sg.flooringmastery.controller.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class TaxDaoImpl implements TaxDao{
    List<Tax> taxRates = new ArrayList<>();
    public static final String TAX_RATES = "taxes.txt";
    public static final String DELIMITER = ",";
    
    @Override
    public List<Tax> getAllTaxRates() throws FileNotFoundException {
        loadTaxInformation();
        return taxRates;            
    }
    @Override
    public double getTaxRate (String stateName) throws FileNotFoundException{
        String s = stateName;
        taxRates = getAllTaxRates();
        double taxRate = 0;
        for (Tax t : taxRates){
            if (t.getStateName().equalsIgnoreCase(s))
                taxRate = t.getTaxRate();
        }
        return taxRate;
    }
    private void loadTaxInformation() throws FileNotFoundException{
        taxRates.clear();
        Scanner scanner;
        scanner = new Scanner(
                  new BufferedReader(
                        new FileReader(TAX_RATES)));
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            Tax t = new Tax();
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            t.setStateName(currentTokens[0]);
            t.setTaxRate(Double.parseDouble(currentTokens[1]));            
            taxRates.add(t);
            }
    }
    
}
