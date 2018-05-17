/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dto.Product;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
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
public class ProductDaoImpl implements ProductDao {

    public static final String PRODUCT_LIST = "products.txt";
    public static final String DELIMITER = "::";
    List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() throws Exception {
        loadAllProducts();
        return products;
    }

    @Override
    public Product getProduct(String productName) throws NotValidChoiceException {
        Product product = new Product();
        boolean isProduct = false;
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(productName)) {
                product = p;
                isProduct = true;
            }
        }
        if(!isProduct){
            throw new NotValidChoiceException("Please select a valid product");
        }
        
        return product;
    }
    
    

    private void loadAllProducts() throws FileNotFoundException {
        Scanner scanner;
        products.clear();
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_LIST)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("-_- could not load  data into memory");
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product p = new Product();
            p.setProductName(currentTokens[0]);
            p.setSqFtCost(Double.parseDouble(currentTokens[1]));
            products.add(p);
        }
    }

}
