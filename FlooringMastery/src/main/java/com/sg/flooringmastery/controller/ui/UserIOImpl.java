/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller.ui;
import com.sg.flooringmastery.controller.service.NotValidChoiceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;



public class UserIOImpl implements UserIO {
    private Scanner input = new Scanner(System.in);
    LocalDate date;

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        boolean valid = false;
        double result = 0;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = Double.valueOf(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value %s is not a number", value);
            }
        } while (!valid);
        return result; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt) {
        boolean valid = false;
        int result = 0;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = Integer.parseInt(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value %s is not a number", value);
            }
        } while (!valid);
        return result; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean valid = false;
        int result = 0;
        do {
            result = readInt(prompt);
            if (result >= min && result <= max) {
                valid = true;
            } else {
                System.out.printf("The value must be between %d and %d. ", min, max);
            }
        } while (!valid);

        return result;
    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean valid = false;
        BigDecimal moneyIn = null;
        do {
            String x = readString(prompt);
            try{               
                moneyIn = new BigDecimal(x);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value %s is not a number", x);
            }
        } while (!valid);
    
    return moneyIn ;
}

    @Override
    //want to be able to read from text file MMDDYYY
    public LocalDate getDate(int year, int month, int day) {
        LocalDate date = LocalDate.of(month,day,year);
        return date;
    }

    @Override
    public LocalDate enterDate() throws NotValidChoiceException{
        System.out.println("Please enter date DD/MM/YYYY");
        int day = readInt("Day:");
        if (day < 1 || day > 31){
            throw new NotValidChoiceException("Not valid day please try again");
        }
        int month = readInt("Month:");
        if (month < 0 || month > 12){
            throw new NotValidChoiceException("Not valid month please restart");
        }
        int year = readInt("Year:");
        if (year < 1000){
            throw new NotValidChoiceException("Not valid year please restart");
        }
        LocalDate date =  LocalDate.of(year, month, day);
        
        return date; //To change body of generated methods, choose Tools | Templates.
    }
    
}
