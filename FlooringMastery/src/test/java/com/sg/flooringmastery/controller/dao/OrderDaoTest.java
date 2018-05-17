/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author apprentice
 */
public class OrderDaoTest {
    
    OrderDaoImpl orderDao = new OrderDaoImpl();
    LocalDate date = LocalDate.of(2000, 1, 1);
    
    
    public OrderDaoTest() {
    }
    /**
     * Test of getOrders method, of class OrderDao.
     * @throws java.lang.Exception
     */
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
    @Test
    public void testGetOrders() throws Exception {  
        //created file for testing 2000-01-01
        //contains "made up" orders. 
        
        List<Order> orders = orderDao.getOrders(date);
        System.out.println(orders.size());
        assertTrue(orders.size() == 2);        
    }
    /**
     * Test of addOrderToFile method, of class OrderDao.
     */
    @Test
    public void testAddOrderToFile() throws Exception {
        
    }
    /**
     * Test of getEditToOrder method, of class OrderDao.
     */
    @Test
    public void testvalidEditToOrder() throws Exception {        
        Order order = orderDao.getEditToOrder(2, date);
        String s = order.getCustomerName();
        assertTrue(s.equalsIgnoreCase("Nicholas"));             
    }
    
    @Test
    public void testnotValidEditToOrder() throws NotValidChoiceException, FileNotFoundException, Exception{
        try{
            orderDao.getEditToOrder(5, date);
            fail("Should not get to this point, not 5 orders in this file");
        }
        catch (NotValidChoiceException | FileNotFoundException ex){
            
        }
    }
    /**
     * Test of removeOrder method, of class OrderDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order order = new Order();
        order.setOrderNumber(3);
        order.setCustomerName("Timmmy");
        
        orderDao.addOrderToFile(order, date);
        List<Order> orders = orderDao.getOrders(date);
        int i = orders.size();
        orderDao.removeOrder(date, 3);
        orders = orderDao.getOrders(date);
        int a = orders.size();        
        assertTrue(i > a);
        
    }

    
}
