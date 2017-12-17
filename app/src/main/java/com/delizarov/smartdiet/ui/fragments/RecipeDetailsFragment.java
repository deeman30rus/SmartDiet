package com.delizarov.smartdiet.ui.fragments;


import com.delizarov.smartdiet.domain.models.Recipe;

public abstract class RecipeDetailsFragment extends BaseFragment {

    public static final String PRIMARY_COLOR = "PrimaryColor";

    protected Recipe mRecipe;

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }
}
