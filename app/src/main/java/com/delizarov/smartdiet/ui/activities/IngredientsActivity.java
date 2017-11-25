package com.delizarov.smartdiet.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Ingredient;
import com.delizarov.smartdiet.presentation.ingredient.IngredientsPresenter;
import com.delizarov.smartdiet.presentation.ingredient.IngredientsView;
import com.delizarov.smartdiet.ui.fragments.IngredientListFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class IngredientsActivity extends BaseActivity implements IngredientsView {

    @Inject
    IngredientsPresenter presenter;

    private IngredientListFragment mIngredientsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);

        getApplicationComponent().inject(IngredientsActivity.this);

        ButterKnife.bind(IngredientsActivity.this);

        mIngredientsFragment = IngredientListFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.ingredients_list, mIngredientsFragment, IngredientListFragment.TAG)
                .commit();

        presenter.attachView(IngredientsActivity.this);
        presenter.onCreate();
    }

    @Override
    public void renderIngredient(Ingredient ingredient) {

        mIngredientsFragment.addIngredient(ingredient);
    }
}
