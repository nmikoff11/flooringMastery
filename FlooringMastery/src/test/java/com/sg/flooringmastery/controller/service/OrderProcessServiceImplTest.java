/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.service;

import com.sg.flooringmastery.controller.dao.OrderDaoImpl;
import com.sg.flooringmastery.controller.dao.ProductDaoImpl;
import com.sg.flooringmastery.controller.dao.TaxDaoImpl;
import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.dto.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrderProcessServiceImplTest {
   LocalDate date = LocalDate.of(2000,1,1);
   
   OrderProcessServiceImpl service;
    public OrderProcessServiceImplTest() {
         ProductDaoImpl productDao = new ProductDaoImpl();
            TaxDaoImpl taxDao = new TaxDaoImpl();
            OrderDaoImpl orderDao = new OrderDaoImpl();
            
            service = new OrderProcessServiceImpl(productDao, taxDao, orderDao);
    }
    
    /**
     * Test of orderTotals method, of class OrderProcessServiceImpl.
     */
    @Test
    public void testOrderTotals() throws Exception {
        //make sure that totals calculate with just 4 pieces of information
        try{Order o = new Order();
        o.setCustomerName("Tim");
        o.setSizeOfArea(100);
        o.setOrderDate(date);
        o.setStateName("Minnesota");
        o.setProductType("marble");
        service.orderTotals(o);
        }
        catch (Exception ex){
            fail("Should have calculated totals");
        }
        
    }

    /**
     * Test of standardlaborCost method, of class OrderProcessServiceImpl.
     */
    @Test
    public void testStandardlaborCost() {
        int r = 10;
        BigDecimal i = new BigDecimal(2.99);
        BigDecimal a = new BigDecimal(0);
         a = service.standardlaborCost();
        r = i.compareTo(a);
        assertTrue(r == 0);         
    }/**
     * Test of productCost method, of class OrderProcessServiceImpl.
     */
    @Test
    public void testAvailableProductCost() throws Exception {
        try{
        String s = "marble";    
        Double area = 10.0;
        BigDecimal cost = new BigDecimal(0);
        cost = service.productCost(s,area);
        }
        catch(Exception ex){
            fail("should not fail");
        }        
    }
    @Test
    public void testGetValidOrder() throws Exception {
        Order order = new Order();
        order = service.getOrder(1, date);
        assertNotNull(order);        
    }
    @Test
    public void testGetNotValidOrder() throws Exception{
        try{
        Order order = new Order();
        order = service.getOrder(5, date);   
        fail("Should not find an order");
        }
        catch(Exception ex){
            System.out.println("Order does not exist");
        }
    }
    /**
     * Test of getProductList method, of class OrderProcessServiceImpl.
     */
    @Test
    public void testGetProductList() throws Exception {
        List<Product> products = new ArrayList<>();
        products = service.getProductList();
        int l =  products.size();
        assertTrue(l > 5);
    }
 
    /**
     * Test of addOrder method, of class OrderProcessServiceImpl.
     */
    @Test
    public void testBadAddOrder() throws Exception {
        Order order = new Order();
        try{
            LocalDate badDate = LocalDate.of(13, 17, 1);
            service.addOrder(order,badDate);
            fail("Should not add with this date");
        }
        catch(Exception ex){
            System.out.println("Date entered is not valid date");
        }
    }

    /**
     * Test of getAllOrders method, of class OrderProcessServiceImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders = service.getAllOrders(date);
        assertTrue(orders.size() == 2);
    }
    
}
