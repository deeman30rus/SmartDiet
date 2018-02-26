package com.delizarov.smartdiet.ui.fragments;


import com.delizarov.smartdiet.domain.models.Recipe;

public abstract class RecipeDetailsFragment extends BaseFragment {

    public static final String PRIMARY_COLOR = "PrimaryColor";

    protected Recipe recipe;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
