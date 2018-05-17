/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.ui;

import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.dto.Product;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringView {

    private UserIO io;
    LocalDate date;
    Order order;
    DecimalFormat formatter = new DecimalFormat("###########.##");

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void welcomeMessage() {
        io.print("__________________________________________");
        io.print(" --Welcome to Nick's Flooring Ordering-- ");
        io.print("__________________________________________");
       
    }

    public int startMenu() {
        io.print("1. Display Orders     |");
        io.print("2. Add an Order       |");
        io.print("3. Edit an Order      |");
        io.print("4. Remove an Order    |");
        io.print("5. Quit               |");
        io.print("______________________\n");

        return io.readInt("Please select: ");
    }

    //for display method function in the controller.
    public LocalDate getOrderInformation() throws NotValidChoiceException, Exception {
        return io.enterDate();
    }

    public void printProductList(List<Product> products) throws FileNotFoundException {
        System.out.println("Products================Price per/Sqft");
        if (products != null) {
            for (Product p : products) {
            System.out.print("" + p.getProductName());
            //for nicer console output
            int s = p.getProductName().length();
            for (int i = 0; i < 24 - s; i++){
                System.out.print(" ");
            }
            System.out.print("$"+(p.getSqFtCost()));
            System.out.print("\n");
            }
            System.out.println("_______________________");
        }
    }

    public void printOrders(List<Order> orders) throws NotValidChoiceException {
        if (orders != null) {
            for (Order s : orders) {
                System.out.println("Order #" + s.getOrderNumber()
                        + "| Customer: " + s.getCustomerName());
            }
            System.out.println("\n");
        } else {
            throw new NotValidChoiceException("Not a valid order date");
        }
    }

    //getting variable information for the service layer on the order calculation. 
    public Order addOrder() throws NotValidChoiceException, Exception {
        
            Order order = new Order();

            String name = io.readString("Customer name:");
            String name1 = name.substring(0, 1).toUpperCase();
            String nameCap = name1 + name.substring(1);
            
            LocalDate dates = io.enterDate();
            
            boolean isArea = false;
            while (!isArea){
            Double areaOfProject = io.readDouble("Total sqFt: ");
            if(areaOfProject > 0){
                isArea = true;
                order.setSizeOfArea(areaOfProject);
            }
            else{
                throw new NotValidChoiceException("Please enter in a valid area.");
            }
            }
            
            String state = io.readString("State: ");
            String state1 = state.substring(0, 1).toUpperCase();
            String stateCap = state1 + state.substring(1);

            String productType = io.readString("Product name: ");
            String product1 = productType.substring(0, 1).toUpperCase();
            String productCap = product1 + productType.substring(1);
            
            order.setCustomerName(nameCap);
            order.setStateName(stateCap);
            order.setOrderDate(dates);
            order.setProductType(productCap);
                    
        return order;
    }

    public Order printOrderInformationForConfirmation(Order order) {
        DecimalFormat formatter = new DecimalFormat("###########.##");
        io.print("====Order Overview====");
        io.print("Order Date: " + "           " + order.getOrderDate());
        io.print("Customer Name:"+"         " + order.getCustomerName());
        io.print("State: " +"                "+ order.getStateName());
        io.print("Tax Rate:    " +"          " + order.getTaxRate() * 100 + "%");
        io.print("Product Type:"+ "          " + order.getProductType());
        io.print("Square Footage"+"         "+ order.getSizeOfArea() + " sqFt");
        io.print("Material cost:  "+ "       $" +  formatter.format(order.getMaterialCost()));
        io.print("Labor cost per SqFt:  " +" $" + order.getStandardLabor());
        io.print("Labor cost:  "+ "          $" + formatter.format(order.getLaborCost()));
        io.print("Tax total:   "+ "          $" + formatter.format(order.getTaxTotal()));
        io.print("                       " + "__________");
        io.print("Order total:  "+"         $" + formatter.format(order.getOrderTotal()));
        io.print("===================");
        return order;
    }

    public boolean submitOrder() {
        boolean submit = false;
        String s = io.readString("Does this order look correct? Yes/No: ");
        if (s.equalsIgnoreCase("yes")) {
            submit = true;
            io.print("***Order Submitted***\n");
            io.print("=======================");
        }
        else{
            io.print("=======================");
            io.print("***Order Not Submitted***");
        }
        return submit;
    }

    public void editOrderBanner() {
        io.print("EDIT ORDER");
        io.print("==========");
        io.print("Please have order date and order number");
    }

    public LocalDate getEditDate() throws NotValidChoiceException, Exception {
        return io.enterDate();

    }

    public int getOrderNumber() {
        return io.readInt("What was the order number? ");
    }

    public Order getOrderEditInformation(Order order) throws Exception {

        String c = io.readString("Enter customer name (" + order.getCustomerName() + ")|");
        if (c.length() > 0) {
            order.setCustomerName(c);
        }
        String s = io.readString("Enter state (" + order.getStateName() + ")|");
        if (s.length() > 0) {
            order.setStateName(s);
        }
        String p = io.readString("Enter product type (" + order.getProductType() + ")|");
        if (p.length() > 0) {
            order.setProductType(p);
        }
        String a = io.readString("Enter area (" + order.getSizeOfArea() + ")|");
        if (a.length() > 0) {
            order.setSizeOfArea(Double.parseDouble(a));

        }
        
        System.out.println("Please enter date of file you would like to save to.");
        LocalDate date = io.enterDate();
        order.setOrderDate(date);
        return order;
    }

    public void exitBanner() {
        io.print("===Thanks for using Nicks Flooring!===");
        io.print("GoodBye!!!");
    }

}
