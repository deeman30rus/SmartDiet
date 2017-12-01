package com.delizarov.smartdiet.domain.exceptions;



public class IngredientNameEmptyException extends Exception {

    public IngredientNameEmptyException() {
        super("Ingredient name is empty");
    }
}
