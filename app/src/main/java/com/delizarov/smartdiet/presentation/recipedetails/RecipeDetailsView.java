package com.delizarov.smartdiet.presentation.recipedetails;


import com.delizarov.smartdiet.domain.models.Recipe;

public interface RecipeDetailsView {
    void applyThemeResource(int resId);

    void displayRecipe(Recipe recipe);
}
