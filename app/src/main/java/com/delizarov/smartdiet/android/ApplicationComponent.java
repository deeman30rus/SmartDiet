package com.delizarov.smartdiet.android;


import com.delizarov.smartdiet.data.DataRepositoryModule;
import com.delizarov.smartdiet.ui.activities.GroceriesActivity;
import com.delizarov.smartdiet.ui.activities.LoginActivity;
import com.delizarov.smartdiet.ui.activities.RecipesActivity;
import com.delizarov.smartdiet.ui.activities.StartActivity;
import com.delizarov.smartdiet.ui.fragments.GroceryListFragment;
import com.delizarov.smartdiet.ui.fragments.RecipeListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
            ApplicationModule.class,
            DataRepositoryModule.class
    }
)
public interface ApplicationComponent {

    void inject(SmartDietApplication application);

    void inject(StartActivity startActivity);

    void inject(LoginActivity loginActivity);

    void inject(GroceriesActivity ingredientsActivity);

    void inject(GroceryListFragment groceryListFragment);

    void inject(RecipesActivity recipesActivity);

    void inject(RecipeListFragment recipeListFragment);
}