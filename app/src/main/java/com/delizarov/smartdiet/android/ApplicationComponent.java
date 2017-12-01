package com.delizarov.smartdiet.android;


import com.delizarov.smartdiet.data.DataRepositoryModule;
import com.delizarov.smartdiet.ui.activities.IngredientsActivity;
import com.delizarov.smartdiet.ui.activities.LoginActivity;
import com.delizarov.smartdiet.ui.activities.StartActivity;
import com.delizarov.smartdiet.ui.fragments.IngredientListFragment;

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

    void inject(IngredientsActivity ingredientsActivity);

    void inject(IngredientListFragment ingredientListFragment);
}