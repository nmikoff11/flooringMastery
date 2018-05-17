/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.dao;

import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author apprentice
 */
public class OrderDaoImpl implements OrderDao {

    List<Order> orders = new ArrayList<>();
    LocalDate date;
    public static final String DELIMITER = ",";
    Order order = new Order();

    @Override
    public List<Order> getOrders(LocalDate date) throws FileNotFoundException, NotValidChoiceException {
        try{
        loadOrders(date);
        return orders;
        }
        catch( FileNotFoundException e){
        System.out.println(e.getMessage() + " Please enter a previous order date"); 
        return orders;
        }
    }
    @Override
    public void addOrderToFile(Order order, LocalDate date) throws IOException, NotValidChoiceException {
        try {
            loadOrders(date);
            orders.add(order);
            writeOrder(date);
        } catch (FileNotFoundException e) {
            orders.add(order);
            writeOrder(date);
        }
    }

    @Override
    public Order getEditToOrder(int orderNumber, LocalDate orderDate) throws FileNotFoundException, IOException, NotValidChoiceException {
        boolean isOrder = false;
        LocalDate d = orderDate;
        for (Order o : getOrders(d)) {
            if ((o.getOrderNumber() == orderNumber)) {
                isOrder = true;
                return o;
            }
        }
        if (!isOrder) {
            throw new NotValidChoiceException("Not a valid order choice");
        }
        return null;
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws FileNotFoundException, IOException, NotValidChoiceException {
        LocalDate d = orderDate;
        Order toRemove = null;
        for (Order o : getOrders(d)) {
            if ((o.getOrderNumber() == orderNumber)) {
                o.setOrderDate(d);
                toRemove = o;
            }
        }
        if (toRemove != null) {
            orders.remove(toRemove);
            //for(Order o : orders){
                //if(o.getOrderNumber() >= toRemove.getOrderNumber()){
                //o.setOrderNumber(o.getOrderNumber() - 1);
                //}
            //}
        } else {
            throw new NotValidChoiceException("Must be existing order to delete. Please try again.");
        }
        writeOrder(d);
    }

    private void loadOrders(LocalDate date) throws FileNotFoundException, NotValidChoiceException {
        String ORDER_DATE = "Orders_" + date + ".txt";
        orders.clear();
        Scanner scanner;
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(ORDER_DATE)));
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) { 
            Order o = new Order();
            currentLine = scanner.nextLine();            
            currentTokens = currentLine.split(DELIMITER);
            o.setOrderNumber(Integer.parseInt(currentTokens[0]));
            o.setCustomerName(currentTokens[1]);
            o.setStateName(currentTokens[2]);
            o.setTaxRate(Double.parseDouble(currentTokens[3]));
            o.setProductType(currentTokens[4]);
            o.setSizeOfArea(Double.parseDouble(currentTokens[5]));
            o.setCostPerSqFt(Double.parseDouble(currentTokens[6]));
            o.setStandardLabor(Double.parseDouble(currentTokens[7]));
            o.setMaterialCost(Double.parseDouble(currentTokens[8]));
            o.setLaborCost(Double.parseDouble(currentTokens[9]));
            o.setTaxTotal(Double.parseDouble(currentTokens[10]));
            o.setOrderTotal(Double.parseDouble(currentTokens[11]));
            orders.add(o);
        
        }

    }

    public void writeOrder(LocalDate date) throws NotValidChoiceException, IOException {
        String ORDER_DATE = "Orders_" + date + ".txt";
        PrintWriter out;
        out = new PrintWriter(new FileWriter(ORDER_DATE));
        for (Order o : orders) {
            out.printf(o.getOrderNumber() + DELIMITER
                    + o.getCustomerName() + DELIMITER
                    + o.getStateName() + DELIMITER
                    + o.getTaxRate() + DELIMITER
                    + o.getProductType() + DELIMITER
                    + o.getSizeOfArea() + DELIMITER
                    + o.getCostPerSqFt() + DELIMITER
                    + o.getStandardLabor() + DELIMITER
                    + o.getMaterialCost() + DELIMITER
                    + o.getLaborCost() + DELIMITER
                    + o.getTaxTotal() + DELIMITER
                    + o.getOrderTotal() + DELIMITER + "\n");

            out.flush();
        }
        out.close();
    }
}
