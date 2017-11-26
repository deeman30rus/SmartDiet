package com.delizarov.smartdiet.presentation.ingredient;


import com.delizarov.smartdiet.domain.models.Ingredient;

public interface IngredientsView {
    void renderIngredient(Ingredient ingredient);

    void showIngredientDetails(Ingredient ingredient);

    void clearIngredients();
}
