package com.delizarov.smartdiet.ui.activities;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.Recipe;
import com.delizarov.smartdiet.presentation.recipes.RecipesPresenter;
import com.delizarov.smartdiet.presentation.recipes.RecipesView;
import com.delizarov.smartdiet.ui.fragments.RecipeListFragment;
import com.delizarov.smartdiet.utils.models.MaterialShade;
import com.github.clans.fab.FloatingActionButton;

import java.io.IOException;

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

    private RecipeListFragment mRecipesFragment;


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
        mRecipesFragment = RecipeListFragment.newInstance();
        mRecipesFragment.setOnClickListener(recipe -> presenter.onRecipeItemClicked(recipe));

//        mRecipesFragment.setOnItemClickListener(recipe -> presenter.onGroceryItemClicked(ingredient));
//        mRecipesFragment.setOnItemDeleteClickListener(ingredient -> presenter.onGroceryDeleteClicked(ingredient));

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.recipe_list, mRecipesFragment, RecipeListFragment.TAG)
                .commit();


//        mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        addRecipe.setOnClickListener(v -> presenter.onAddButtonClick());

        presenter.attachView(RecipesActivity.this);
        presenter.onViewCreated();
    }

    @Override
    public void renderRecipe(Recipe recipe) {

        mRecipesFragment.addRecipe(recipe);
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {

        Intent intent = new Intent(getApplicationContext(), RecipeDetailsActivity.class);

        MaterialShade primaryColor = getPrimaryColor(recipe);

        intent.putExtra(RecipeDetailsActivity.COLOR_SHADE, primaryColor);
        intent.putExtra(RecipeDetailsActivity.RECIPE_ID, recipe.getId());

        startActivity(intent);
    }

    /**
     * наиболее подходящий цвет 500 оттенка, выбраный из ресурсов
     */
    private MaterialShade getPrimaryColor(Recipe recipe) {

        AssetManager am = getAssets();

        Bitmap bitmap;

        MaterialShade color = MaterialShade.Indigo500;

        try {
            bitmap = BitmapFactory.decodeStream(am.open(recipe.getMainPicture()));

            Palette palette = Palette.generate(bitmap);

            color = MaterialShade.nearest(palette.getVibrantColor(getResources().getColor(R.color.indigo_500)));

        } catch (IOException e) {

            return color;
        }

        return color;
    }
}
