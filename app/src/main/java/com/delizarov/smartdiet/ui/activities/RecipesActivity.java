package com.delizarov.smartdiet.ui.activities;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.presentation.recipes.RecipesPresenter;
import com.delizarov.smartdiet.presentation.recipes.RecipesView;
import com.delizarov.smartdiet.ui.fragments.RecipeListFragment;
import com.github.clans.fab.FloatingActionButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends BaseActivity implements RecipesView {

    @Inject
    RecipesPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_recipe)
    FloatingActionButton addRecipe;

    private RecipeListFragment mGroceriesFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipes);

        ButterKnife.bind(this);

        getApplicationComponent().inject(this);

        // toolbar

        toolbar.setTitle(R.string.activity_recipes_title);

        setSupportActionBar(toolbar);

        // recipes
        mGroceriesFragment = RecipeListFragment.newInstance();

//        mGroceriesFragment.setOnItemClickListener(recipe -> presenter.onGroceryItemClicked(ingredient));
//        mGroceriesFragment.setOnItemDeleteClickListener(ingredient -> presenter.onGroceryDeleteClicked(ingredient));

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.recipe_list, mGroceriesFragment, RecipeListFragment.TAG)
                .commit();


//        mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //

        addRecipe.setOnClickListener(v -> presenter.onAddButtonClick());

        presenter.attachView(RecipesActivity.this);
        presenter.onViewCreated();
    }

    @Override
    public void renderRecipe(Recipe recipe) {

        mGroceriesFragment.addRecipe(recipe);
    }
}
