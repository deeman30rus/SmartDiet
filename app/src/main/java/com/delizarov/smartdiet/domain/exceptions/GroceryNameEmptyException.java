package com.delizarov.smartdiet.domain.exceptions;



public class GroceryNameEmptyException extends Exception {

    public GroceryNameEmptyException() {
        super("Grocery name is empty");
    }
}
