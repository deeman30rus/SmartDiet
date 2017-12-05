package com.delizarov.smartdiet.presentation.recipes;


import com.delizarov.smartdiet.domain.models.Recipe;

public interface RecipesView {
    void renderRecipe(Recipe recipe);
}
