package com.expenses;

public class InvalidExpenseException extends RuntimeException{
//    void invalidExpenseException(String message){
//        System.out.println(message);
//    }
    public InvalidExpenseException(String message){
        super(message);
    }
}
