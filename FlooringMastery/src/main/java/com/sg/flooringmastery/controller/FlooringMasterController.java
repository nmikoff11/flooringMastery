/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.controller.dao.OrderDao;
import com.sg.flooringmastery.controller.dao.OrderDaoImpl;
import com.sg.flooringmastery.controller.dao.ProductDao;
import com.sg.flooringmastery.controller.dao.ProductDaoImpl;
import com.sg.flooringmastery.controller.dao.TrainingOrderDaoImpl;
import com.sg.flooringmastery.controller.dto.Order;
import com.sg.flooringmastery.controller.dto.Product;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import com.sg.flooringmastery.controller.service.OrderProcessServiceImpl;
import com.sg.flooringmastery.controller.ui.FlooringView;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterController {

    private FlooringView view;
    private OrderProcessServiceImpl service;
    private OrderDao orderDao;
    private ProductDao productDao;
    private TrainingOrderDaoImpl trainingDao;

    Order order = new Order();
    //private final List<Order> orders = new ArrayList<>();
    //private List<Product> products = new ArrayList<>();

    public FlooringMasterController(OrderDao orderDao, OrderProcessServiceImpl service, ProductDao productDao,
            FlooringView view) {
        this.orderDao = orderDao;
        this.service = service;
        this.productDao = productDao;
        this.view = view;
    }

    public void run() throws Exception {
        boolean keepGoing = true;
        int menuSelection = 0;
        view.welcomeMessage();
        try {
            while (keepGoing) {
                menuSelection = getUserSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrder();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        getEditOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exitMenu();
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("Please select a valid command.\n"
                                + "Please try again");
                }
            }
        } catch (FileNotFoundException | NotValidChoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    //get selection for run()
    private int getUserSelection() {
        return view.startMenu();
    }

    private void displayOrder() throws Exception {
        view.printOrders(service.getAllOrders(view.getOrderInformation()));
    }

    private void addOrder() throws Exception, NotValidChoiceException {

        view.printProductList(service.getProductList());
        try {
            order = view.addOrder();
            order = view.printOrderInformationForConfirmation(service.orderTotals(order));
            boolean submit = view.submitOrder();
            if (submit) {
                service.addOrder(order, order.getOrderDate());
            }
        } catch (NotValidChoiceException e) {
            System.out.println(e.getMessage());
            System.out.println("\n");
        }

    }

    private void getEditOrder() throws Exception, NotValidChoiceException {
        view.editOrderBanner();
        try {
            int i = view.getOrderNumber();
            LocalDate date = view.getEditDate();
            order = service.getOrder(i, date);
            order = view.getOrderEditInformation(order);
            order = service.orderTotals(order);
            view.printOrderInformationForConfirmation(order);
            boolean submit = view.submitOrder();
            if (submit) {
                service.orderRemove(date, i);
                order = service.orderTotals(order);
                service.addOrder(order, date);
            }
        } catch (NotValidChoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeOrder() throws Exception, NotValidChoiceException {
        view.editOrderBanner();
        int i = view.getOrderNumber();
        LocalDate date = view.getEditDate();
        service.orderRemove(date, i);
        System.out.println("Order removed succesfully!");
    }

    private void exitMenu() {
        view.exitBanner();
    }
}
