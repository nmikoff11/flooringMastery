/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dto.Product;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class ProductDaoImplTest {
    
    ProductDao productDao = new ProductDaoImpl();
    
    public ProductDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllProducts method, of class ProductDaoImpl.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = productDao.getAllProducts();
        assertTrue(products.size() > 5);
    }

    /**
     * Test of getProduct method, of class ProductDaoImpl.
     * @throws com.sg.flooringmastery.controller.service.NotValidChoiceException
     */
    @Test
    public void testGetProduct() throws NotValidChoiceException {
        try{
        productDao.getProduct("Tiger");
            fail("Should not get here");
                }
        catch(NotValidChoiceException e){
            
        }
    }
    
}
