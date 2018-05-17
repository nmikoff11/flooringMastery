/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.service;

/**
 *
 * @author apprentice
 */
public class NotValidChoiceException  extends Exception{
   
    public NotValidChoiceException(String message){
        super(message);
    }
    public NotValidChoiceException(String message, Throwable cause){
        super(message, cause);
    }
}

